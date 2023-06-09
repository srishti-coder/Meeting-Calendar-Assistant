> # Meeting Calendar Assistant
Clone project and configure Application properties.
# APIs<br>
## Add Users <br>
 API: http://localhost:9999/meet/addUser <br>
 input: <br>
 ```
   [
       {
            "name":"srishti",
            "email":"srishti@gmail.com"
        },
        {
            "name":"akash",
            "email":"akash@gmail.com"
        },
        {
            "name":"Abinav",
            "email":"abhinav@gmail.com"
        }
   ]
 ```
 ## Create Meeting <br>
 API: http://localhost:9999/meet/addMeeting <br>
 input: <br>
 ```
  {
    "dateTime":"2023-03-07T06:30:00.00",
    "userId": [1,3]
  }
 ```
 ## Users with Meeting Conflicts <br>
 http://localhost:9999/meet/scheduleConflicts <br>
 output: <br>
 ```
  [
    {
        "name": "srishti",
        "email": "srishti@gmail.com",
        "dateTime": "2023-03-07T06:30:00",
        "userList": [
            [
                {
                    "name": "nitish",
                    "email": "nitish@gmail.com",
                    "meetId": 1
                },
                {
                    "name": "akash",
                    "email": "akash@gmail.com",
                    "meetId": 1
                }
            ],
            [
                {
                    "name": "nitish",
                    "email": "nitish@gmail.com",
                    "meetId": 2
                },
                {
                    "name": "Abinav",
                    "email": "abhinav@gmail.com",
                    "meetId": 2
                }
            ]
        ]
    }
]
 ```
 ## Users with free slot (Gives next 10 free slots) <br>
 http://localhost:9999/meet/freeSlots <br>
 input: <br>
 ```

    {
    "userId": [1,3]
    }

 ```
  output: <br>
 ```

   [
    {
        "slotNo": "Slot no: 1",
        "dateTime": "2023-03-02T23:30:52.8385695"
    },
    {
        "slotNo": "Slot no: 2",
        "dateTime": "2023-03-03T00:00:52.8385695"
    },
    {
        "slotNo": "Slot no: 3",
        "dateTime": "2023-03-03T00:30:52.8385695"
    },
    {
        "slotNo": "Slot no: 4",
        "dateTime": "2023-03-03T01:00:52.8385695"
    },
    {
        "slotNo": "Slot no: 5",
        "dateTime": "2023-03-03T01:30:52.8385695"
    },
    {
        "slotNo": "Slot no: 6",
        "dateTime": "2023-03-03T02:00:52.8385695"
    },
    {
        "slotNo": "Slot no: 7",
        "dateTime": "2023-03-03T02:30:52.8385695"
    },
    {
        "slotNo": "Slot no: 8",
        "dateTime": "2023-03-03T03:00:52.8385695"
    },
    {
        "slotNo": "Slot no: 9",
        "dateTime": "2023-03-03T03:30:52.8385695"
    },
    {
        "slotNo": "Slot no: 10",
        "dateTime": "2023-03-03T04:00:52.8385695"
    }
]

 ```
