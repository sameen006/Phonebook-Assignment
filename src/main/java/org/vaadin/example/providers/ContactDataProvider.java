package org.vaadin.example.providers;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.SortDirection;
import org.vaadin.example.ContactInfoServiceInMemoryImpl;
import org.vaadin.example.model.ContactInfoModel;
import org.vaadin.example.repository.ContactServiceDBImpl;
import org.vaadin.example.service.ContactInfoService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Comparator.naturalOrder;

public class ContactDataProvider
		extends AbstractBackEndDataProvider<ContactInfoModel, CrudFilter> {

	private final ContactInfoService service = new ContactInfoServiceInMemoryImpl();

	public  ConcurrentHashMap<Integer,ContactInfoModel> DATABASE = service.getContacts();

	private Consumer<Long> sizeChangeListener;

	@Override
	protected Stream<ContactInfoModel> fetchFromBackEnd(Query<ContactInfoModel, CrudFilter> query) {
		int offset = query.getOffset();
		int limit = query.getLimit();

		Stream<ContactInfoModel> stream = DATABASE.values().stream();

		if (query.getFilter().isPresent()) {
			stream = stream.filter(predicate(query.getFilter().get()))
					.sorted(comparator(query.getFilter().get()));
		}

		return stream.skip(offset).limit(limit);
	}

	@Override
	protected int sizeInBackEnd(Query<ContactInfoModel, CrudFilter> query) {
		// For RDBMS just execute a SELECT COUNT(*) ... WHERE query
		long count = fetchFromBackEnd(query).count();

		if (sizeChangeListener != null) {
			sizeChangeListener.accept(count);
		}

		return (int) count;
	}

	void setSizeChangeListener(Consumer<Long> listener) {
		sizeChangeListener = listener;
	}

	private static Predicate<ContactInfoModel> predicate(CrudFilter filter) {
		// For RDBMS just generate a WHERE clause
		return filter.getConstraints().entrySet().stream()
				.map(constraint -> (Predicate<ContactInfoModel>) contactInfo -> {
					try {
						Object value = valueOf(constraint.getKey(), contactInfo);
						return value != null && value.toString().toLowerCase()
								.contains(constraint.getValue().toLowerCase());
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}).reduce(Predicate::and).orElse(e -> true);
	}

	private static Comparator<ContactInfoModel> comparator(CrudFilter filter) {
		// For RDBMS just generate an ORDER BY clause
		return filter.getSortOrders().entrySet().stream().map(sortClause -> {
			try {
				Comparator<ContactInfoModel> comparator = Comparator.comparing(
						person -> (Comparable) valueOf(sortClause.getKey(),
								person));

				if (sortClause.getValue() == SortDirection.DESCENDING) {
					comparator = comparator.reversed();
				}

				return comparator;

			} catch (Exception ex) {
				return (Comparator<ContactInfoModel>) (o1, o2) -> 0;
			}
		}).reduce(Comparator::thenComparing).orElse((o1, o2) -> 0);
	}

	private static Object valueOf(String fieldName, ContactInfoModel person) {
		try {
			Field field = ContactInfoModel.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(person);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public String persist(ContactInfoModel model, ContactInfoModel orgModel) {

		return service.persist(model, orgModel);

	}

	public void delete(ContactInfoModel model) {
		service.delete(model);
	}

}
