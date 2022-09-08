# Emir-Todd-P1

## Index
- Technology  <br/>
- Project Description <br/>
- Product Usage <br/>
- Status Flow Chart <br/>
- User Story <br/>
- Roles <br/>
- ER diagram <br/>
- Future Development <br/>
- Contributors <br/>

## Technology:
- Written in Java 8. <br/>
- Intellij <br/>
- Amazon AWS <br/>
- Apache Maven <br/>
- Apache Tomcat <br/>
- JSON Webtoken <br/>
- Jackson Core <br/>
- Postman <br/>
- Docker <br/>
- Dbeaver <br/>
- Postgres <br/>
- Junit <br/>
- Mockito

## Project Description:
 We were tasked with building a back-end API that will support a new internal expense reimbursement system.
This system manages the process of reimbursing of employees for expenses incurred while on company time. 
All registered employees can login and submit requests for reimbursement with their reimbursement details. 
They can also view past reimbursement submissions and pending requests. On the other end Finance managers 
can log in and view all reimbursements requests, sort them by status or type and the history of all closed 
reimbursement requests. Finance managers are authorized to make a decision on these requests for the expense reimbursement.


## Product Usage:
- Testing (todd) <br/>
- Password Encryption (emir) <br/>
- Login (todd/emir) <br/>
  - Admin <br/>
    1. Add new User (todd) <br/>
    2. Update target User's active state (todd/emir)<br/>
    3. Delete target User (emir)<br/>
    4. Reset a target User's password (todd/emir)
  - Finance Manger <br/>
    1. View all Reimbursements (todd/emir)<br/>
    2. Sort Reimbursements by Status/Type (todd/emir)<br/>
    3. Sort Reimbursements by Pending/History (todd/emir)<br/>
    4. View Reimbursement Details (todd/emir)<br/>
    5. Approve/Deny Reimbursement (todd/emir)<br/>
  - Employee <br/>
    1. Submit new Reimbursement (todd/emir)<br/>
    2. View own Reimbursements (todd/emir)<br/>
    3. View own Reimbursement Details (todd/emir)<br/>
    4. Update pending Reimbursement (todd/emir)<br/>
    5. View own Reimbursement Pending/History (todd/emir)

![System Use Case Diagrams](https://raw.githubusercontent.com/220207-java-enterprise/assignments/main/foundations-project/imgs/ERS%20Use%20Case%20Diagram.png)

## Reimbursement Status Flow: 
![Reimbursment Status State Flow](https://raw.githubusercontent.com/220207-java-enterprise/assignments/main/foundations-project/imgs/ERS%20State%20Flow%20Diagram.png)

## User Story:
As someone who frequently travels for work Emir and Todd's iers system allowed me to be able to submit a reimbursement request and get back 
the money I have spent traveling for business!

As a Financial Manager, I wanted an easier to use way to view all my employee's reimbursements, sort them, so I am able to approve or deny them 
based on the criteria provided. Thankfully Todd and Emir's iers system is just the perfect system!

## Roles:
- Admin <br/>
- Finance Manger <br/>
- Employee


## Entity Relationship Diagram:
![Relational Model](https://github.com/220207-java-enterprise/assignments/blob/main/foundations-project/imgs/ERS%20Relational%20Model.png)



## Future Developments:
-  Add functionality to receipt to be able to upload a receipt image
- Additional functionality to add multiple receipts to a reimbursement request
- More testing to ensure data security
- Implement a front end to make it a better user experience
- Add Jenkins functionality...

## Contributors:
- Todd Kirby
- Emir Kaynak

