package org.vaadin.example;

import org.vaadin.example.model.ContactInfoModel;
import org.vaadin.example.service.ContactInfoService;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ContactInfoServiceInMemoryImpl implements ContactInfoService {


	public static int key = 1;
	private static ConcurrentHashMap<Integer, ContactInfoModel> contactInfoMap;
	static {

		contactInfoMap = new ConcurrentHashMap<>();
		contactInfoMap.put(key++,
				new ContactInfoModel(1,"sameen","javed",
						"Lahore","Pakistan","58","sameen@gmail.com","03044685257",false,0));
		contactInfoMap.put(key++, new ContactInfoModel(2,"hira","khalid",
				"Lahore","Pakistan","58","hira@gmail.com","03044685457",false,0));

		contactInfoMap.put(key++, new ContactInfoModel(3,"Adeel","Ahmad",
				"Lahore","Pakistan","58","adeel@gmail.com","03044696457",false,0));
	}

	@Override
	public Boolean isExistingPhoneNumber(String phoneNumber, ContactInfoModel model) {
		Optional<ContactInfoModel> contactInfoModel =contactInfoMap.values().stream().filter(entity -> entity.getPhoneNumber().equals(phoneNumber))
				.findFirst();
		return contactInfoModel.isPresent() && contactInfoModel.get().getId()!= model.getId();
	}

	@Override
	public void delete(ContactInfoModel model) {
		contactInfoMap.entrySet().removeIf(entity -> entity.getValue().getId().equals(model.getId()));
	}

	@Override
	public String persist(ContactInfoModel model, ContactInfoModel orgModel) {

		if (model.getId() == null )
			return addNewContact(model, orgModel);

		else {
			if (isExistingPhoneNumber(model.getPhoneNumber(),orgModel))
				return "Contact already exist in phone book";

			Integer position = contactInfoMap.entrySet().stream().filter(entry -> entry.getValue().getId().equals(model.getId())).findFirst().get().getKey();
			model.setEditMode(false);
			contactInfoMap.remove(position);
			contactInfoMap.put(position, model);
			return "Contact Successfully updated";

		}
	}

	private String addNewContact(ContactInfoModel model , ContactInfoModel orgModel) {
		if (isExistingPhoneNumber(model.getPhoneNumber(), orgModel))
			return "Contact already exist in phone book";

		else{
			model.setEditMode(false);
			model.setId(key + 1);
			model.setEditCount(0);
			contactInfoMap.put(key++, model);
			return "Contact Successfully added";
		}
	}

	@Override
	public ConcurrentHashMap<Integer,ContactInfoModel> getContacts(){
		return contactInfoMap;
	}
}
