#curl -X POST -H 'Content-Type:application/json' http://192.168.1.12:8000/api/sites \
#-d '{ "name": "Joe First", "email": "foobar@example.com", "active": "1", "owner": "1", "created": "2018-10-14"}'

#curl -X POST -H 'Content-Type:application/json' http://192.168.1.12:8000/api/users -d '{ "first_name":"fname", "last_name": "lname", "username": "test1", "email": "hh@here.com", "password":"jason123", "is_staff":"1"}'

#curl -d '{"username":"eddyod", "firstName":"Edward", "lastName":"OD","email":"eodonnell@ucsd.edu","password":"1234567", "phone":"555-1212","superUser":"true","staff":"true","active":"true"}' -H 'Content-Type:application/json' -X POST http://localhost:8080/users/register
curl -X POST -H 'Content-Type:application/json' https://www.mephistosoftware.com/premier-rester/app-login -d '{"email":"eddyod@yahoo.com", "firstName":"Edd", "lastName":"OD"}'
#curl -X POST -H 'Content-Type:application/json' http://localhost:8080/sites/1/locations -d '{"name":"Location 1"}'


