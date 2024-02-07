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


**5- Valid email check**

Email must contain "@" and proper service provider name i.e., "gmail.com"

<img width="866" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/4d4af64b-52d7-4f09-8a4e-cd7d16c41cbf">


**6-Add Contact**

user can add a new contact by clicking new item button and respective record will be added to UI as well as db.

initial data 


<img width="1405" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ae59db23-9660-409d-82ee-dc78eb244389">
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/6972318b-1c3d-492f-8e73-62228008baa9">

adding a new contact from UI.

<img width="864" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/a5fdcd3b-bf1a-491b-9c66-8fa2a816226c">
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ee311812-2ac5-4b0a-a346-9a8c8b5a3389">
<img width="1239" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/1f6f67c8-9c56-44ce-8f2b-0a5476f3b941">


**7-Delete Contact**

By clicking in delete button selected contact will delete from  UI and db.

before deleting
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ee311812-2ac5-4b0a-a346-9a8c8b5a3389">
<img width="1239" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/1f6f67c8-9c56-44ce-8f2b-0a5476f3b941">

after clicking delete button

<img width="860" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/a25f014b-fc44-41dc-909b-45deb284b770">
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/be21fbea-ed50-4a53-88c2-b9243de863fc">
<img width="1406" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/58584895-93a4-4679-bffb-864687ba5d3e">




**8-Update Contact**

Any valid change i.e.,(with no duplicate number) will reflect on UI and db

<img width="1791" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/de144d78-2db0-426f-90d0-568f366266d7">
<img width="865" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/e02f8ffd-893b-4d90-8e12-f1e2c9d635ca">
<img width="1217" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/67d960d6-339c-4803-88fc-9c6920faa62e">



**9-Concurrency handling for warning user that contact is being modified**

If multiple users are editing same contact they will be warned
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/784ed673-7e86-4de7-be9b-f07ed67ca5bd">


