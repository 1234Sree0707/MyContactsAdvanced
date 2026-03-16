package com.seveneleven.service;

import java.util.*;

import com.seveneleven.factory.TagFactory;
import com.seveneleven.session.SessionManager;
import com.seveneleven.model.Tag;
import com.seveneleven.model.Contact;
import com.seveneleven.repository.ContactRepository;
import com.seveneleven.observer.ContactObserver;
import com.seveneleven.observer.TagObserver;
import com.seveneleven.search.SearchCriteria;
import com.seveneleven.filter.ContactFilter;
import com.seveneleven.filter.ContactSortStrategy;

public class ContactService {

	private ContactRepository repo;

	private List<ContactObserver> observers = new ArrayList<>();
	private List<TagObserver> tagObservers = new ArrayList<>();

	public ContactService(ContactRepository repo) {
		this.repo = repo;
	}

	// =========================
	// ADD CONTACT
	// =========================
	public void addContact(Contact contact) {

		if(SessionManager.getInstance().getLoggedInUser() == null){
			System.out.println("Please login first");
			return;
		}

		repo.add(contact);

		System.out.println("Contact added: " + contact.getName());
	}

	// =========================
	// DELETE CONTACT
	// =========================
	public void softDelete(Contact contact) {

		contact.setDeleted(true);

		notifyObservers(contact);
	}

	public void hardDelete(Contact contact) {

		repo.remove(contact);

		notifyObservers(contact);
	}

	// =========================
	// DELETE OBSERVER
	// =========================
	public void registerObserver(ContactObserver observer){
		observers.add(observer);
	}

	private void notifyObservers(Contact contact){

		for(ContactObserver obs : observers){
			obs.onContactDeleted(contact);
		}
	}

	// =========================
	// TAG CONTACTS
	// =========================
	public void tagContacts(List<Contact> contacts, String tagName){

		Tag tag = TagFactory.getTag(tagName);

		for(Contact c : contacts){

			c.addTag(tag);

			notifyTagObservers(c, tag);
		}

		System.out.println("Tag '" + tagName + "' added to " + contacts.size() + " contacts.");
	}

	// =========================
	// TAG OBSERVER
	// =========================
	public void registerTagObserver(TagObserver observer){
		tagObservers.add(observer);
	}

	private void notifyTagObservers(Contact contact, Tag tag){

		for(TagObserver obs : tagObservers){
			obs.onTagAdded(contact, tag);
		}
	}

	// =========================
	// SEARCH CONTACTS
	// =========================
	public List<Contact> searchContacts(SearchCriteria criteria){

		return repo.getAllContacts()
				.stream()
				.filter(criteria::matches)
				.toList();
	}

	// =========================
	// ADVANCED FILTERING
	// =========================
	public List<Contact> filterContacts(ContactFilter filter,
			ContactSortStrategy strategy){

		return repo.getAllContacts()
				.stream()
				.filter(filter::apply)
				.sorted(strategy.getComparator())
				.toList();
	}
	// =========================
	// APPLY TAG TO SINGLE CONTACT
	// =========================
	public void applyTag(Contact contact, Tag tag){

		contact.addTag(tag);

		notifyTagObservers(contact, tag);

		System.out.println("Tag '" + tag.getName() + "' applied to contact " + contact.getName());
	}
}
