# Phonebook-Assignment

Below attached are the screenshots of each functionality that was mention in assignmnent.

**1-Show contacts**

Currently we have 3 contacts in memory and they are also visible on UI.

<img width="1197" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/5a7d4b86-4300-4386-b0cc-114550a93a1f">

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ae37836a-765f-4eb7-95de-e806f9401b42">



**2-Sorting on records**

Both Ascending and descending sort on all 3 columns is working fine. In this image we are sorted on first name is ascending order 

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/f68e02fb-5f2c-47df-b744-1a400fd72258">


In this image we are sorted on last name in descending order 

<img width="1789" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ba5851d4-baf0-4fbf-a8e6-ace7f4ccb24d">


**3-Filtering on records**


Below images are showing sorting on all 3 columns 

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/d18b6768-1ae5-4165-a5e5-7fc9f8b431fe">

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/e46d3df9-c45c-467f-aaf2-dd96e945298b">

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/f9a3df86-7726-45cb-80b7-d31b930a07cc">



**4-Unique number check and Number length check**

Proper checks are added to make sure that phone number for each record in the phonebook is unique. Validation check are also added to make sure number length must be equal to 11 digits. More than 11 digits are not allowed in phone number field. Error will be shown if number of digits will be less than 11.

These checks are handled for both scenarios i.e., creating a new contact OR updating existing record

**i) Creating new contact(unique number validation)**

As "03044685257" phone number already exist for contact name sameen in memory. So user will not be allowed to create a new contact with same number. 

<img width="857" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/d76b6d49-9d27-46a8-8bba-7ebfac2dd4a8">
<img width="859" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/3835ac18-6396-4390-ba44-b4dae09fbb90">



**ii) Creating new contact(number length validation)**

Image showing if number length is less than 11 then an error will appear 

<img width="862" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/8fec6812-d21b-4abe-af02-b44004db44ad">


**iii) Updating existing contact(unique number validation)**

As "03044685257" phone number already exist for contact name sameen in db. So user will not be allowed to create a new contact with same number.So user will not be ale to update existing contact with same number.
image Contact name "hira" is an existing contact with a different number and user cannot update it to anyexisting number. 

<img width="864" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/0a7bcb36-11d4-4606-ae6b-9be6935372ef">


**iv) Updating existing contact (number length validation)**

Number length validation is also added in edit mode as shon in image below. 
<img width="859" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/c39bfa42-28e2-47bb-adbd-bbc0486a98f5">


**5- Valid email check**

Email must contain "@" and proper service provider name i.e., "gmail.com"

<img width="861" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/84c30a1b-dedb-4a2c-93c9-c44d84a13ddc">

**6-Add Contact**

user can add a new contact by clicking new item button and respective record will be added to UI as well as db.

initial data

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ae37836a-765f-4eb7-95de-e806f9401b42">
adding a new contact from UI.

<img width="864" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/f0a9b3de-31e3-48fa-b6a6-e526f22b5c4b">
<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/02f83bba-9214-45e3-9901-ede43d5eb17b">


**7-Delete Contact**

By clicking in delete button selected contact will delete from UI and db.

before deleting 

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/02f83bba-9214-45e3-9901-ede43d5eb17b">

after clicking delete button

<img width="863" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/b28222a9-1df7-4891-9652-16af0515d059">

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/ae37836a-765f-4eb7-95de-e806f9401b42">

**8-Update Contact**

Any valid change i.e.,(with no duplicate number) will reflect on UI and db

<img width="865" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/f6653225-1d56-4997-9b81-786d9dbb7d95">
<img width="1791" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/6b3e4c33-7556-46b9-9991-76c61d2af7e0">

**9-Concurrency handling for warning user that contact is being modified**

If multiple users are editing same contact they will be warned

<img width="1792" alt="image" src="https://github.com/sameen006/Phonebook-Assignment/assets/159250159/d882e682-dae4-4d71-9ced-b9e83d4af897">
