#!/usr/bin/env python
import random
import requests
from faker import Faker
from random import randint, choice
import datetime as dt

fake = Faker()
fake.seed(random.randint(10**9, 10**10-1))
##API_URL = "http://www.mephistosoftware.com/premier-rester"
API_URL = "http://10.195.4.147:8090"
#API_URL = "http://localhost:8090"

def fillSchedule(personId, locationId, amount, token):
    url = '{}/schedule'.format(API_URL)
    for i in range(amount):
        start, end = getRandomStartEnd()
        payRate = randint(12, 50)
        if start < dt.datetime.now():
            samples = [True, True, True, True, True, False]
        else:
            samples = [True]
        completed = choice(samples)
        params = {
            "start": start.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "end": end.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "payRate": str(payRate),
            "locationId": str(locationId),
            "personId": str(personId),
            "completed": str(completed)
            }
        try:
            resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
        except Exception as e:
            print('Error filling schedule',e)

def addSchedule(personId, locationId, amount, token):
    url = '{}/schedule'.format(API_URL)
    for i in range(amount):
        start, end = getRandomStartEnd()
        payRate = randint(12, 50)
        if start < dt.datetime.now():
            samples = [True, True, True, True, True, False]
        else:
            samples = [True]
        completed = choice(samples)
        params = {
            "start": start.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "end": end.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "payRate": str(payRate),
            "locationId": str(locationId),
            "personId": str(personId),
            "completed": str(completed)
            }
        try:
            resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
        except Exception as e:
            print('Error filling schedule',e)

def updateSchedule(token, id, personId, locationId):
    url = '{}/schedule/{}'.format(API_URL, id)
    start, end = getRandomStartEnd()
    payRate = randint(12, 50)
    if start < dt.datetime.now():
        samples = [True, True, True, True, True, False]
    else:
        samples = [True]
    completed = choice(samples)
    params = {
            "start": start.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "end": end.strftime("%Y-%m-%dT%H:%M:%S.%f"),
            "payRate": str(payRate),
            "locationId": str(locationId),
            "personId": str(personId),
            "completed": str(completed)
            }
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error updating schedule.')
    else:
        print(resp.json())

def fillEmployee(token):
    url = API_URL + '/employee'
    fake = Faker()
    params = dict(
        firstName=fake.first_name(),
        lastName=fake.last_name(),
        email=fake.email(),
        phone=fake.phone_number(),
        address=fake.street_address()
    )
    resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    data = resp.json()  # Check the JSON Response Content documentation below
    return data['id']


def fillLocation(token):
    url = API_URL + '/location'
    fake = Faker()
    params = dict(
        name=fake.company(),
        address=fake.street_address(),
        email = fake.email(),
        phone = fake.phone_number(),
        latitude = random.uniform(-86,86),
        longitude = random.uniform(-180, 180)
    )
    id = 1
    resp = requests.post(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        data = resp.json()
        id = data['id']
    return id

def getRandomEmployeeId(token):
    resp = requests.get(API_URL + '/employees', headers={'Authorization': 'JWT {}'.format(token)})
    ids = []
    for _item in resp.json():
        ids.append(_item['id'])
    return choice(ids)


def updateEmployee(token, personId):
    url = '{}/person/{}'.format(API_URL, personId)
    fake = Faker()
    params = dict(
        firstName = fake.first_name(),
        lastName = fake.last_name(),
        email = fake.email(),
        phone = fake.phone_number(),
        address = fake.street_address(),
        active = 1
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        print('Updated employee OK')
    else:
        print('Error updating employee.',resp.status_code)


def attachEmployeeSchool(token, employeeId, locationId):
    # /employee/{employeeId}/{locationId}
    url = '{}/employee/{}/{}'.format(API_URL, employeeId, locationId)
    resp = requests.post(url=url,  headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        print('Updated employee OK')
    else:
        print('Error updating employee.',resp.status_code)


def updateLocation(token, locationId):
    url = '{}/location/{}'.format(API_URL, locationId)
    fake = Faker()
    params = dict(
        name=fake.company(),
        address=fake.street_address()
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error updating location.')

def getRandomLocationId(token):
    try:
        resp = requests.get(API_URL + '/locations', headers={'Authorization': 'JWT {}'.format(token)})
    except Exception as e:
        print('Get Random location failed', e)
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



def getCurrentUser(token, id):
    url = '{}/person/{}'.format(API_URL, id)
    print('getCurrentUser url',url)
    resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)})
    return resp.json()


def getPagedEmployees(token):
    print('\nSearch employees\n')
    url = '{}/employees/paged'.format(API_URL)
    params = dict(
        filter='',
        ordering='',
        limit=2,
        offset=2
        )
    try:
        resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)}, params=params)
        print(resp.json())
    except Exception as e:
        print('Search failed', e)

def getPagedSchedules(token):
    print('\nSearch schedules\n')
    url = '{}/schedules/paged'.format(API_URL)
    params = dict(
        filter='',
        ordering='',
        limit=2,
        offset=2
        )
    try:
        resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)}, params=params)
        print(resp.json())
        """
        for _item in resp.json():
            print('Employee: {} {} {} {} {} {}'.format(_item['id'],
            _item['name'],
            _item['active'],
            _item['phone'],
            _item['address'],
            _item['personType']))
        """
    except Exception as e:
        print('Search failed', e)

def getEmployees(token):
    print('\nGet employees\n')
    url = '{}/persons/employees'.format(API_URL)
    try:
        resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)})
        #print(resp.json())
        
        for _item in resp.json():
            print('Employee: {} {} {} {} {} {}'.format(_item['id'],
            _item['name'],
            _item['active'],
            _item['phone'],
            _item['address'],
            _item['personType']))
        
    except Exception as e:
        print('Search failed', e)

def testEmployees(token):
    resp = requests.get(API_URL + '/employees', headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print('Employee: {} {}'.format(_item['id'], _item['name']))


def searchLocations(token):
    print('\nSearch locations\n')
    url = '{}/locations'.format(API_URL)
    params = dict(
        search='',
        ordering='',
        limit=10,
        offset=0
        )
    try:
        resp = requests.get(url, headers={'Authorization': 'JWT {}'.format(token)}, json=params)
    except Exception as e:
        print('Search locationsfailed', e)

def testLocations(token):
    resp = requests.get(API_URL + '/locations', headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print('Location: {} {} {} {}'.format(_item['id'], _item['name'], _item['latitude'], _item['longitude']))


def testSchedules(token):
    resp = requests.get(API_URL + '/schedules',  headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error getting schedules.',resp.status_code)
    else:
        for _item in resp.json():
            # print(_item)
            print('Schedule: {} {} {} {}'.format(_item['id'], _item['start'], _item['person']['name'], _item['location']['name']))


def getEmployeeSchedule(token, personId):
    url = '{}/schedules/employee/{}'.format(API_URL, personId)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print(_item)
        # print('{} {} {}'.format(_item['employee.id'], _item['start'], _item['end']))


def getScheduleById(token, id):
    url = '{}/schedule/{}'.format(API_URL, id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    print(resp.json())

def getLocationSchedule(token, id):
    url = '{}/schedules/location/{}'.format(API_URL, id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    for _item in resp.json():
        print(_item)
        #print('{} {} {}'.format(_item['location.id'], _item['start'], _item['end']))


def login(email, password):
    url = API_URL + '/login'
    params = dict(email=email, password=password)
    try:
        resp = requests.post(url=url, json=params)
        data = resp.json()  # Check the JSON Response Content documentation below
        return data
    except:
        print('Login failed')



def register(firstName, lastName, email, password):
    url = API_URL + '/register'
    params = dict(
        firstName=firstName,
        lastName=lastName,
        email=email,
        password=password,
        personType = 1)
    try:
        resp = requests.post(url=url, json=params)
        data = resp.json()  # Check the JSON Response Content documentation below
        # print(data)
    except:
        print('Registering failed')


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
    email = 'jasonodonnell@yahoo.com'
    password = 'j1234567'
    firstName = 'Jason'
    lastName = 'ODonnell'
    register(firstName, lastName, email, password)
    data = login(email, password)
    # print(data)
    token = data['token']
    #id = data['id']
    #print('Got token:', token)
    #print('Got id:', id)
    #  insert
    
    
    for i in range(2):

        _ = fillEmployee(token)
        _ = fillLocation(token)

        #locationId = getRandomLocationId(token)
        #updateLocation(token, locationId)

        #personId = getRandomEmployeeId(token)
        #updateEmployee(token, personId)

        #fills = random.randint(15, 50)
        # print(personId, locationId)
        #addSchedule(personId, locationId, fills, token)
        #getEmployeeSchedule(token, personId)
        # getLocationSchedule(token, locationId)

    for i in range(2):
        locationId = getRandomLocationId(token)
        personId = getRandomEmployeeId(token)
        fills = random.randint(1, 5)
        addSchedule(personId, locationId, fills, token)
        
    #  now get data
    # testEmployees(token)
    attachEmployeeSchool(token, personId, locationId)
    testEmployees(token)
    testLocations(token)
    testSchedules(token)
    #person = getCurrentUser(token, id)
    #getScheduleById(token, 1)
    #updateSchedule(token, 1, personId, locationId)
    #getScheduleById(token, 1)
    # print('person',person)

    #getPagedSchedules(token)


if __name__ == '__main__':
    main()
