package org.vaadin.example.service;

import org.vaadin.example.model.ContactInfoModel;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface ContactInfoService {

	public ConcurrentHashMap<Integer, ContactInfoModel> getContacts();

	public void delete(ContactInfoModel model);

	public String persist(ContactInfoModel model, ContactInfoModel orgModel);

	public  Boolean isExistingPhoneNumber(String phoneNumber, ContactInfoModel model);

}
