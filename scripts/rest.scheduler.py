#!/usr/bin/env python
import random
import requests
from faker import Faker
from random import randint, choice
import datetime as dt

fake = Faker()
fake.seed(random.randint(10**9, 10**10-1))
#facebookToken = "EAAOeEUXu74kBABpBPdMynFsVybbaN24cbe3FQH9aEOnabPQkoUm8rJyp8wFHzk2Gb56ZCYimYoFWTTeNRzi1MSEilUEGuhDDNZCq1ZA9yu9Gpp6Q46egamSRIq5ZCqtvZBAhhHMG77q8t5oZATXKIFXOgdz4Rycjw5NsOLsQJapZAYhkcZBapdOytrndBSugZBfL4VZBSGPBgmSwZDZD"

API_URL = "http://www.mephistosoftware.com/premier-rester"
#API_URL = "http://10.195.4.147:8090"
API_URL = "http://localhost:8090"

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
            print('Error filling schedule',e,resp.content)

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
        print('Error updating schedule.', resp.content)
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
    if resp.status_code == 200:
        print('Updated employee OK')
    else:
        print('Error updating employee.',resp.content)

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
        # 32.7510668,-117.1691726,
        latitude = random.uniform(31,34),
        longitude = random.uniform(-119, -116)
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


def updateEmployee(token, personId, email):
    url = '{}/person/{}'.format(API_URL, personId)
    fake = Faker()
    params = dict(
        firstName = fake.first_name(),
        lastName = fake.last_name(),
        email = email,
        phone = fake.phone_number(),
        address = fake.street_address(),
        active = 1
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        print('Updated employee OK')
    else:
        print('Error updating employee.',resp.content)


def attachEmployeeSchool(token, employeeId, locationId):
    # /employee/{employeeId}/{locationId}
    url = '{}/employee/{}/{}'.format(API_URL, employeeId, locationId)
    resp = requests.post(url=url,  headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code == 200:
        print('Updated employee OK')
    else:
        print('Error updating employee.',resp.content)


def updateLocation(token, locationId):
    url = '{}/location/{}'.format(API_URL, locationId)
    fake = Faker()
    params = dict(
        name=fake.company(),
        address=fake.street_address()
    )
    resp = requests.put(url=url, json=params, headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error updating location.', resp.content)

def getRandomLocationId(token):
    try:
        resp = requests.get(API_URL + '/locations', headers={'Authorization': 'JWT {}'.format(token)})
    except Exception as e:
        print('Get Random location failed', e)
    ids = []
    for _item in resp.json():
        ids.append(_item['id'])
    return choice(ids)


def insertEmployee(firstName, lastName, email, token):
    params = dict(
        firstName=firstName,
        lastName=lastName,
        email=email,
        address='123 Main st'
        )
    url = API_URL + '/employee'
    resp = requests.post(url=url, headers={'Authorization': 'JWT {}'.format(token)}, json=params)
    print(resp.content)
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
    print(resp)
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
            print('Schedule: {} {} {} {}'.format(_item['title'], _item['start'], _item['person']['name'], _item['location']['name']))


def testTodaySchedules(token, id):
    url = '{}/schedules/today/{}'.format(API_URL, id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    if resp.status_code != 200:
        print('Error getting schedules.',resp.content)
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

def getLocationById(token, id):
    url = '{}/location/{}'.format(API_URL, id)
    resp = requests.get(url,  headers={'Authorization': 'JWT {}'.format(token)})
    print(resp.json())

def getPersonById(token, id):
    url = '{}/person/{}'.format(API_URL, id)
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
        print('Login failed', resp)



def register(firstName, lastName, email, password):
    url = API_URL + '/register'
    params = dict(
        firstName=firstName,
        lastName=lastName,
        email=email,
        password=password,
        personType = 999)
    try:
        resp = requests.post(url=url, json=params)
        data = resp.json()  # Check the JSON Response Content documentation below
        #print(data)
    except:
        print('Registering failed')

def socialLogin(email, firstName, lastName):
    url = '{}/app-login'.format(API_URL)
    data = ''
    params = dict(email=email, firstName=firstName, lastName=lastName)
    try:
        resp = requests.post(url=url, json=params)
        print(resp)
        data = resp.json()  # Check the JSON Response Content documentation below
    except:
        data = 'app login failed'
    return data


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
    #token = facebookToken
    #register(firstName, lastName, email, password)
    #data = socialLogin(email, firstName, lastName)
    data = login(email, password)
    print(data)
    token = data['token']
    #id = data['id']
    #print('Got token:', token)
    #print('Got id:', id)
    #  insert
    #insertEmployee(firstName, lastName, email, token)
    #insertEmployee(firstName, lastName, "joe", token)

    
    for i in range(0):

        _ = fillEmployee(token)
        _ = fillLocation(token)

        locationId = getRandomLocationId(token)
        #updateLocation(token, locationId)

        personId = getRandomEmployeeId(token)
        #updateEmployee(token, personId)

        fills = random.randint(2, 5)
        print(personId, locationId)
        addSchedule(personId, locationId, fills, token)
        #getEmployeeSchedule(token, personId)
        # getLocationSchedule(token, locationId)

    for i in range(0):
        locationId = getRandomLocationId(token)
        personId = getRandomEmployeeId(token)
        fills = random.randint(5, 5)
        addSchedule(1, locationId, fills, token)
        
    #  now get data
    # testEmployees(token)
    #attachEmployeeSchool(token, personId, locationId)
    testEmployees(token)
    #testLocations(token)
    print()
    #testSchedules(token)
    #testTodaySchedules(token, 1)
    #person = getCurrentUser(token, id)
    #getScheduleById(token, "asd")
    #getLocationById(token, "dd")
    #getPersonById(token, "X1")
    #updateSchedule(token, 1, 1, 666)
    #updateEmployee(token, 1, 'joe')
    #updateLocation(token, 11111)
    # print('person',person)

    #getPagedSchedules(token)


if __name__ == '__main__':
    main()
