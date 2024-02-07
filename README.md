# Phonebook-Assignment
In branch "database" phone book assignment is implemented using MySql db.

Below attached are the screenshots of each functionality that was mention in assignmnent.

**1-Show contacts**
  Currently we have 3 contacts in db and they are also visible on UI.
  <img width="1225" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/16d58280-92dc-4b57-964b-672fba69b8f7">
  <img width="1789" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/1728ec1d-3a90-4ea2-b845-e0d83d0fe2f8">


**2-Sorting on records**

Both Ascending and descending sort on all 3 columns is working fine.
In this image we are sorted on first name is ascending order
<img width="1789" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/74244146-982d-42e5-b91d-a0e4b358cee4">

In this image we are sorted on last name in descending order
<img width="1786" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/6d683531-d3e9-4038-90fd-000825689031">


**3-Filtering on records**
Below images are showing sorting on all 3 columns
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/7f6edc24-1ae6-4197-a8e1-0a4e69af2856">
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/3361e55b-769d-4284-a062-e70446616b09">
<img width="1787" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/de11bb54-2ed6-4c53-a8bf-68e223fc1507">

**4-Unique number check and Number length check**
Proper checks are added to make sure that phone number for each record in the phonebook is unique. Validation check are also added to make sure number length must be equal to 11 digits. More than 11 digits are not allowed in phone number field. Error will be shown if number of digits will be less than 11.

These checks are handled for both scenarios i.e., creating a new contact OR updating existing record

      **i) Creating new contact(unique number validation)**
      
              As "03344685256" phone number already exist for contact name sameen in db. So user will not be allowed to create a new contact with same number.
              <img width="1093" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/b022ece0-86e0-4ae9-bd89-457fc27651e3">
              <img width="855" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/e075dbab-e20b-4389-a383-3d142b5d2049">
      
      
          
      **ii) Creating new contact(number length validation)**
          Image showing if number length is less than 11 then an error will appear
          <img width="868" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/78a4dbc1-6c40-4e4b-9c35-3f37d8840642">
      
      **iii) Updating existing contact(unique number validation)**
            As "03344685256" phone number already exist for contact name sameen in db. So user will not be allowed to create a new contact with same number.So user will not be ale to update existing contact with same number.
              <img width="1093" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/b022ece0-86e0-4ae9-bd89-457fc27651e3">
              Contact name "hira" is  an existing contact with a different number and user cannot update it to anyexisting number.
              <img width="1375" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/a3841f8d-4b81-453d-9710-2993c500188b">
      
              <img width="860" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/725d2771-a00d-4d1c-847a-c4c0ee3402e1">
      
      
      **iv) Updating existing contact (number length validation)**
        Number length validation is also added in edit mode as shon in image below.
        <img width="861" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/8250469f-0fa8-4031-90c9-98f505065f84">


5- Valid email check
<img width="866" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/4d4af64b-52d7-4f09-8a4e-cd7d16c41cbf">



**6-Add Contact**

7-Delete Contact
8-Update Contact

9-Concurrency handling for warning user that contact is being modified

