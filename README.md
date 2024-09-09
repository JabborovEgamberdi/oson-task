Assalomu Alaykum !

Docker Hub uchun link:
https://hub.docker.com/repository/docker/jabborovegamberdi/oson-task/general

Command for download image:
docker push jabborovegamberdi/oson-task:v1

API docs:

###
GET http://localhost:8080/api/v1/tasks

Response:
Status: 200
[
  {
    "id": 1,
    "title": "Task 1",
    "description": "Task 1",
    "dueDate": "2024-09-09 15:15:15",
    "status": "OPEN"
  },
  {
    "id": 2,
    "title": "Task 1",
    "description": "Task 1",
    "dueDate": "2024-09-09 15:15:15",
    "status": "OPEN"
  }
]

###
GET http://localhost:8080/api/v1/tasks/1

Response:
Status: 200
{
    "id": 1,
    "title": "Task 1",
    "description": "Task 1",
    "dueDate": "2024-09-09 15:15:15",
    "status": "OPEN"
}

###
POST http://localhost:8080/api/v1/tasks
Content-Type: application/json

{
  "title": "Task 1",
  "description": "Task 1",
  "dueDate": "2024-09-09T15:15:15"
}

Response:
Status: 201
{
  "id": 1,
  "title": "Task 1",
  "description": "Task 1",
  "dueDate": "2024-09-09 15:15:15",
  "status": "OPEN"
}

###
PUT http://localhost:8080/api/v1/tasks/1
Content-Type: application/json

{
  "title": "Task 1",
  "description": "Task 1",
  "dueDate": "2024-09-09T15:15:15",
  "status": "IN_PROGRESS"
}

Response: 
Status: 201
{
  "id": 1,
  "title": "Task 1",
  "description": "Task 1",
  "dueDate": "2024-09-09 15:15:15",
  "status": "IN_PROGRESS"
}

###
DELETE http://localhost:8080/api/v1/tasks/1
Response: Status 204
