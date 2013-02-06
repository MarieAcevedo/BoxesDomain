A domain client wants to activate his account

Meta:
@author Marie Acevedo (http://www.sojanddesign.com/)
@version 01 Fev. 2013

Narrative: 
In order to connect and use Boxes
As a client program
I want to activate his account on Boxes

Scenario:  account activation on Boxes

Given Inactive user account created previously with sojanddesign@gmail.com
When It calls domain boxes to activate his account
Then Boxes must activate the user whose email address is sojanddesign@gmail.com
And the user should be active
And the user must be initialized with defaults boxes labeled inbox, trash, next actions, less than two minutes actions and incubator

Scenario:  expiration of activation account

Given expiration@example.com of inactive user account created previously 4 days ago
When It calls domain boxes to activate his account
Then Boxes must identify the activation confirmation has expired
And Boxes must cancel the activation launching an com.sojanddesign.boxes.domain.model.user.ExpirationActivationUserException with The activation request has exceeded the period of 3 days for expiration@example.com