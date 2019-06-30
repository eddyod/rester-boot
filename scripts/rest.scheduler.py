#!/usr/bin/env python
import random
import requests
from faker import Faker
from random import randint, choice
import datetime as dt

fake = Faker()
fake.seed(random.randint(10**9, 10**10-1))
API_URL = "http://localhost:8090"
# API_URL = "http://10.195.4.147:8090"


def testUsers():
    resp = requests.get(API_URL + '/user_sites?user_id=2')
    for _item in resp.json()['results']:
        print('{} {} {}'.format(_item['id'],
                                _item['first_name'],
                                _item['last_name'],
                                _item['username']))


def fillSchedule(employee_id, location_id, amount, token):
    url = '{}/schedules/{}/{}'.format(API_URL, employee_id, location_id)
    for i in range(amount):
        start, end = getRandomStartEnd()
        pay_rate = randint(12, 50)
        if start < dt.datetime.now():
            samples = [True, True, True, True, True, False]
        else:
            samples = [True]
        completed = choice(samples)
        params = {
            "start": start.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "end": end.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "payRate": str(pay_rate),
            "location": {"id": str(location_id)},
            "person": {"id": str(employee_id)},
            "completed": str(completed)
            }
        requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})


def fillEmployee(token):
    url = API_URL + '/persons'
    fake = Faker()
    params = dict(
        firstName = fake.first_name(),
        lastName = fake.last_name(),
        email=fake.email()
    )
    resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    data = resp.json()  # Check the JSON Response Content documentation below
    return data['id']


def getRandomEmployeeId(token):
    resp = requests.get(API_URL + '/employees', headers={'Authorization': 'JWT {}'.format(token)})
    ids = []
    for _item in resp.json():
        ids.append(_item['id'])
    return choice(ids)


def updateEmployee(token, employee_id):
    url = '{}/persons/{}'.format(API_URL, employee_id)
    fake = Faker()
    params = dict(
        firstName = fake.first_name(),
        lastName = fake.last_name(),
        email = fake.email()
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        print('Success!')
    else:
        print('Error.')

def fillLocation(token):
    url = API_URL + '/locations'
    fake = Faker()
    params = dict(
        name=fake.company(),
        address=fake.street_address()
    )
    resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    data = resp.json()  # Check the JSON Response Content documentation below
    return data['id']

def updateLocation(token, location_id):
    url = API_URL + '/locations'
    url = '{}/locations/{}'.format(API_URL, location_id)
    fake = Faker()
    params = dict(
        name=fake.company(),
        address=fake.street_address()
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error.')

def getRandomLocationId(token):
    resp = requests.get(API_URL + '/locations', headers={'Authorization': 'JWT {}'.format(token)})
    ids = []
    for _item in resp.json():
        ids.append(_item['id'])
    return choice(ids)


def insertUser(first_name, last_name, username, email, password, token):
    print('Inserting user:', first_name, last_name, username, email, password)
    params = dict(
        first_name=first_name,
        last_name=last_name,
        username=username,
        email=email,
        password=password
        )
    url = API_URL + 'api/users'
    resp = requests.post(url=url, headers={'Authorization': 'JWT {}'.format(token)}, json=params)
    data = resp.json()  # Check the JSON Response Content documentation below
    print(data)


def getCurrentUser(token, id):
    url = '{}/persons/{}'.format(API_URL, id)
    resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)})
    return resp.json()


def testEmployees(token):
    resp = requests.get(API_URL + '/persons', headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print('{} {} {}'.format(_item['id'], _item['firstName'], _item['lastName']))


def testLocations(token):
    resp = requests.get(API_URL + '/locations', headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print('{} {}'.format(_item['id'], _item['name']))


def testSchedules(token):
    resp = requests.get(API_URL + '/schedules',  headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print('{} {} {}'.format(_item['id'], _item['start'], _item['end']))

def getEmployeeSchedule(token, employee_id):
    url = '{}/schedules/employee/{}'.format(API_URL, employee_id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print(_item)
        #print('{} {} {}'.format(_item['employee.id'], _item['start'], _item['end']))

def getLocationSchedule(token, id):
    url = '{}/schedules/location/{}'.format(API_URL, id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print(_item)
        #print('{} {} {}'.format(_item['location.id'], _item['start'], _item['end']))


def login(email, password):
    url = API_URL + '/login'
    params = dict(email=email, password=password)
    resp = requests.post(url=url, json=params)
    data = resp.json()  # Check the JSON Response Content documentation below
    return data


def register(firstName, lastName, email, password):
    url = API_URL + '/register'
    params = dict(
        firstName=firstName, 
        lastName=lastName, 
        email=email, 
        password=password,
        personType = 1)
    resp = requests.post(url=url, json=params)
    data = resp.json()  # Check the JSON Response Content documentation below
    return data['id']


def random_date(start, end):
    return start + dt.timedelta(
        # Get a random amount of seconds between `start` and `end`
        seconds=random.randint(0, int((end - start).total_seconds())),
    )


def getRandomStartEnd():
    startDate = dt.datetime.now() - dt.timedelta(days=120)
    endDate = dt.datetime.now() + dt.timedelta(days=120)
    startSchedule = random_date(startDate, endDate)
    endSchedule = startSchedule + dt.timedelta(hours=1)
    return startSchedule, endSchedule


def main():
    """Main method"""
    email = 'joe@user.com'
    password = 'joe12345'
    firstName = 'joe'
    lastName = 'Imauser'
    #id = register(firstName, lastName, email, password)
    #print('Got ID of:', id)
    data = login(email, password)
    print('data',data)
    token = data['token']
    id = data['id']
    #print('Got token:', token)
    #print('Got id:', id)


    #  insert
    """
    for i in range(10):
        _ = fillEmployee(token)
        _ = fillLocation(token)
        
        location_id = getRandomLocationId(token)
        updateLocation(token, location_id)
        
        employee_id = getRandomEmployeeId(token)
        updateEmployee(token, employee_id)
        
        fills = random.randint(2, 15)
        #print(employee_id, location_id)
        fillSchedule(employee_id, location_id, fills, token)
        getEmployeeSchedule(token, employee_id)
        getLocationSchedule(token, location_id)
        
    """
    #  now get data
    #testEmployees(token)
    #testLocations(token)
    #testSchedules(token)
    person = getCurrentUser(token, id)
    print('person',person)


if __name__ == '__main__':
    main()
