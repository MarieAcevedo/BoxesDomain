A domain client wants to create an account on Boxes

Meta:
@author Marie Acevedo (http://www.sojanddesign.com/)
@version 31 Janv. 2013

Narrative: 
In order to use Boxes
As a client program
I want to create an account on Boxes
     
Scenario:  registration on Boxes
 
Given personal information of the user account to be created: sojanddesign@gmail.com, sojanddesign and motdepasse
And sojanddesign@gmail.com is not already registered on Boxes
When It calls domain boxes to create an account
Then Boxes must create a user with the account settings whose value sojanddesign@gmail.com, sojanddesign and motdepasse
And the user should not be active
And Boxes must be transmitted a notification to request the sending mail account activation (PENDING)

Scenario:  a user account already exists
 
Given personal information of the user account to be created: marie@example.com, alreadyExist and motdepasse
And marie@example.com is already registered on Boxes
When It calls domain boxes to create an account
Then Boxes must identifiy that marie@example.com already exists
And Boxes must throw an com.sojanddesign.boxes.domain.model.user.UserAlreadyExistException with A user is already registered for email adress : marie@example.com

