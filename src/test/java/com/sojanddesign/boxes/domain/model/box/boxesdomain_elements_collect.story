A client wants to collect some elements in the "Inbox"

Meta:
@author Marie Acevedo (http://www.sojanddesign.com/)
@version 06 Feb. 2013

Narrative:
In order to treat the stuff
As a client domain
I want to collect elements to process

Scenario: put in the "inbox" some elements

Given the active user login to boxes with sojanddesign@gmail.com
And an element ready to be collected entitled : lire le livre de Adam Bien
When It calls domain boxes to put in the INBOX the given element
Then Boxes must create an element entitled lire le livre de Adam Bien
And the element must be contained in box with type INBOX
And the element must not be activate and done
