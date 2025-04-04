# Scheduler Web Application

## Schedule API Documents

## < 일정 생성 : Post >

일정을 생성하여 DB에 저장합니다.

### Request

#### 1. Request URL
localhost:8080/schedules

#### 2. RequestType : RequestBody(jason)

#### 3. Request Elements


| Parameter | Request Type | Parameter or Field Type | Required | Description  |
|:---------:|--------------|:-----------------------:|:-----:|:------------:|
|  로그인 여부   | Session      |                  |    O  |              |
|   title   |              |         String          |    O  |    일정 제목     |
|   task    |              |         String          |    X  |    일정 내용     |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
        "id": 1,
        "title": "test1",
        "task": "taskTest1",
        "createdAt": "2025-04-02T11:32:25.8605737",
        "modifiedAt": "2025-04-02T11:32:25.8605737"
        }
```
#### 3. Http Status Massage
|          Status           |   HttpStatus    |             Message             |  
|:-------------------------:|:---------------:|:-------------------------------:|
|           일정 등록           |   201 CREATED   |                X                |
|     유저 정보를 찾을 수 없는 경우     |  404 NOT_FOUND  |      "User Not Found"      |
|    email 형식에 맞지 않은 경우     | 400 BAD_REQUEST | "email": "올바른 형식의 이메일 주소여야 합니다" |
|  일정 제목 = null or 공백인 경우   | 400 BAD_REQUEST |      "Invalid Input Value"      |
| 각 Field와 다른 타입의 요청을 받을 경우 | 400 BAD_REQUEST |  "Invalid Type Value"  |

## < 전체 일정 조회 : Get >

전체 일정들을 페이징하여 조회 할 수 있습니다.

### Request

#### Request URL
localhost:8080/schedules/pages

#### 2. RequestType : Request Param

#### 3. Request Elements

|        | Parameter Type | Required | Description |
|:------:|:--------------:|:--------:|:-----------:|
| 로그인 여부 |                |    x     |             |
|  Page  |      int       |    O     |   현재 페이지    |
|  Size  |      int       |    O     |    일정 개수    |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {
        "title": "test3",
        "userName": "오애순",
        "task": "taskTest3",
        "createdAt": "2025-04-04T22:05:42.406543",
        "modifiedAt": "2025-04-04T22:05:42.406543",
        "countComment": 0
        },
        {
        "title": "test2",
        "userName": "오애순",
        "task": "taskTest2",
        "createdAt": "2025-04-04T22:05:37.512594",
        "modifiedAt": "2025-04-04T22:05:37.512594",
        "countComment": 0
        },
        {
        "title": "test1",
        "userName": "오애순",
        "task": "taskTest1",
        "createdAt": "2025-04-04T22:05:32.6742",
        "modifiedAt": "2025-04-04T22:05:32.6742",
        "countComment": 0
        }
        ]
```
#### 3. Http Status Massage
|          Status           |   HttpStatus    |       Massage        |  
|:-------------------------:|:---------------:|:--------------------:|
|            조회             |     200 OK      |          X           |
|     조회 결과가 존재하지 않을 경우     |  404 NOT_FOUND  | "Schedule Not Found" |
| default = ?page=0&size=10 |

## <단건 조회 : Get >

일정 고유 식별 번호로 개별 일정을 조회 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/schedules/each/{scheduleId}

#### 2. RequestType : PathVariable

#### 3. Request Elements

|            |  Request Type  | Parameter or fieldType | Required | Description |
|:----------:|:--------------:|:----------------------:|:--------:|-------------|
| scheduleId |  PathVariable  |          Long          |    O     | 일정 고유 식별 번호 |
|   로그인 여부   |    Session     |                        |    X     |             |


### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
    "id": 1,
    "title": "test1",
    "task": "taskTest1",
    "createdAt": "2025-04-02T11:43:47.947639",
    "modifiedAt": "2025-04-02T11:43:47.947639"
}
```
#### 3. Http Status Massage
|           Status           |  HttpStatus   |         Massage         |  
|:--------------------------:|:-------------:|:-----------------------:|
|             조회             |    200 OK     |            X            |
|     조회 결과가 존재하지 않을 경우      | 404 NOT_FOUND |  "Schedule Not Found"   |
|  작성자 식별 번호 타입과 다른 요청의 경우   | 400 BAD_REQUEST | "Invalid Type Value" |
|      작성자 식별 번호 = null      | 400 BAD_REQUEST |   "Invalid Input Value"   |

## < 일정 수정 : Patch >

일정 제목, 일정 내용을 수정할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/schedules/individuals/{scheduleId}

#### 2. RequestType : Path Variable, Request Body

#### 3. Request Elements

|            | RequestType  | Parameter Or Field Type | Required | Description |
|:----------:|:------------:|:-----------------------:|:--------:|:-----------:|
|   로그인 여부   |   Session    |                         |    O     |             |
| ScheduleId | PathVariable |          Long           |    O     | 일정 고유 식별 번호 |
|   title    | RequestBody  |         String          |    X     |    일정 제목    |
|    task    | RequestBody  |         String          |    X     |    일정 내용    |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {//old
        "id": 3,
        "title": "test3",
        "task": "taskTest3",
        "createdAt": "2025-04-04T22:05:42.406543",
        "modifiedAt": "2025-04-04T22:05:42.406543"
        },
        {//new
        "id": 3,
        "title": "updatetitle1",
        "task": "updataetask1",
        "createdAt": "2025-04-04T22:05:42.406543",
        "modifiedAt": "2025-04-04T22:05:42.406543"
        }
]
```
#### 3. Http Status Massage
|             Status             |   HttpStatus    | Massage |  
|:------------------------------:|:---------------:|:-------:|
|               수정               |     200 OK      |    X    |
|      수정 요청 일정이 존재하지 않는 경우      |  404 NOT_FOUND  |"Schedule Not Found"|
|   로그인 유저의 유저 정보를 찾을 수 없는 경우    |  404 NOT_FOUND  |   "User Not Found"|
| 로그인 유저가 수정 대상 일정에 대한 인가가 없는 경우 | 400 BAD_REQUEST |"Unauthorized Access"|
|        작성자 식별 번호 = null        | 400 BAD_REQUEST |   "Invalid Input Value" |
|       모든 Field가 null일 경우       |400 BAD_REQUEST | "모든 필드를 null로 설정할 수 없습니다."|
|   각 Field와 다른 타입의 요청을 받을 경우    | 400 BAD_REQUEST |"Invalid Type Value"|

## < 일정 삭제 : Delete >

일정을 삭제 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/schedules/individuals/{id}

#### 2. RequestType : PathVariable

#### 3. Request Elements

|            | RequestType  |  Type  | Required | Description |
|:----------:|:------------:|:------:|:--------:|:-----------:|
| scheduleId | PathVariable |  Long  |    O     | 일정 고유 식별 번호 |
|   로그인 여부   |   Session    |        |    O     |             |

### Response

#### Http Status Massage
|             Status             |  HttpStatus   |        Massage        |  
|:------------------------------:|:-------------:|:---------------------:|
|               삭제               |    200 OK     |           X           |
|로그인 유저의 유저 정보를 찾을 수 없는 경우|404 NOT_FOUND|   "User Not Found"    |
| 로그인 유저가 수정 대상 일정에 대한 인가가 없는 경우 | 400 BAD_REQUEST | "Unauthorized Access" |
|      삭제 요청 일정이 존재하지 않는 경우      | 404 NOT_FOUND | "Schedule Not Found"  |
|    작성자 식별 번호 타입과 다른 요청의 경우     | 400 BAD_REQUEST | "Invalid Type Value"  |
|        작성자 식별 번호 = null        | 400 BAD_REQUEST | "Invalid Input Value" |

## User API Documents

## < 회원가입 : Post >

사용자 정보를 DB에 등록합니다.

### Request

#### 1. Request URL
localhost:8080/users

#### 2. RequestType : RequestBody

#### 3. Request Elements

|           | Request Type | Parameter or Field Type | Required | Description |
|:---------:|:------------:|:-----------------------:|:--------:|----------|
|  로그인 여부   |              |                         |    X     |          |
| userName  |              |         String          |    O     | 사용자 이름   |
|   email   |              |         String          |    O     | 사용자 email |
| password  |              |         String          |    0     | 비밀번호 |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
        "id": 1,
        "userName": "오애순",
        "email": "zxc@uio.com",
        "createdAt": "2025-04-04T22:04:56.4693021",
        "modifiedAt": "2025-04-04T22:04:56.4693021"
        }
```
#### 3. Http Status Massage
|          Status           |   HttpStatus  |             Message            |  
|:-------------------------:|:-------------:|:------------------------------:|
|          사용자 등록           |   201 CREATED |                X               |
|  email = null or 공백인 경우   | 400 BAD_REQUEST |      "Invalid Input Value"     |
|    email 형식에 맞지 않은 경우     | 400 BAD_REQUEST | "email": "올바른 형식의 이메일 주소여야 합니다" |
|      email이 중복 된 경우       |"Bad Request"|"Email is Duplicated"|
|  사용자 이름 = null or 공백인 경우  | 400 BAD_REQUEST |    "Invalid Input Value"    |
|   비밀번호 = null or 공백인 경우   |400 BAD_REQUEST|   "Invalid Input Value"     |
| 각 Field와 다른 타입의 요청을 받을 경우 | 400 BAD_REQUEST |  "Invalid Type Value" |

## < 로그인 : Post >

사용자 email과 password로 로그인 합니다.

### Request

#### 1. Request URL
localhost:8080/users/login

#### 2. RequestType : RequestBody

#### 3. Request Elements

|          | Request Type |  Type  | Required | Description |
|:--------:|--------------|:------:|:--------:|----------|
|  로그인 여부  |              |        |    X     |           |
|  email   |              | String |    O     | 사용자 email |
| password |              | String |    0     | 비밀번호 |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
        "id": 1,
        "loginUserName": "오애순",
        "loginMessage": "안녕하세요! 오애순님 환영합니다."
        }
```
#### 3. Http Status Massage
|          Status         |   HttpStatus    |             Message           |  
|:-----------------------:|:---------------:|:-----------------------------:|
|            로그인          |     200 OK      |    안녕하세요! "userName"님 환영합니다.  |
|  email = null or 공백인 경우 | 400 BAD_REQUEST |      "Invalid Input Value"    |
|    email 형식에 맞지 않은 경우   | 400 BAD_REQUEST | "email": "올바른 형식의 이메일 주소여야 합니다" |
|  email이 일치하는 회원정보가 없는 경우|  404 NOT_FOUND  |"User Not Found"|
|   비밀번호 = null or 공백인 경우 | 400 BAD_REQUEST |   "Invalid Input Value"    |
|  비밀번호가 회원 정보와 일치하지 않는 경우 | 400 BAD_REQUEST "Invalid Password"|
| 각 Field와 다른 타입의 요청을 받을 경우 | 400 BAD_REQUEST |  "Invalid Type Value" |

## < 전체 사용자 조회 : Get >

모든 사용자 정보들을 조회 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/users/total

#### 2. Request Elements

|          |  Request Type  |  Type  | Required | Description |
|:--------:|:--------------:|:------:|:--------:|----------|
|  로그인 여부  |    Session     |        |    O     |           |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {
        "id": 1,
        "userName": "박효성",
        "email": "qwer@asdf.com",
        "createdAt": "2025-04-02T11:43:42.169888",
        "modifiedAt": "2025-04-02T11:43:42.169888"
        },
        {
        "id": 2,
        "userName": "김두한",
        "email": "123@456.com",
        "createdAt": "2025-04-02T12:00:11.98394",
        "modifiedAt": "2025-04-02T12:00:11.98394"
        },
        {
        "id": 3,
        "userName": "오애순",
        "email": "zxc@uio.com",
        "createdAt": "2025-04-02T12:00:45.16158",
        "modifiedAt": "2025-04-02T12:00:45.16158"
        }
        ]
```
#### 3. Http Status Massage
|           Status      |   HttpStatus    |        Massage         |  
|:---------------------:|:---------------:|:----------------------:|
|             조회        |     200 OK      |           X            |
|     조회 결과가 존재하지 않을 경우 |  404 NOT_FOUND  | "User Not Found" |

## < 사용자 고유 식별 번호를 통한 사용자 조회 : Get >

사용자 고유 식별 번호로 사용자 정보를 개별 조회 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/users/each/{userId}

#### 2. RequestType : PathVariable

#### 3. Request Elements

| PathVariable | Request Type | Type | Required | Description  |
|:------------:|:------------:|:----:|:--------:|--------------|
|    로그인 여부    |   Session    |      |    O     |              |
|    userId    |              | Long |    O     | 사용자 고유 식별 번호 |


### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
        "id": 1,
        "userName": "오애순",
        "email": "zxc@uio.com",
        "createdAt": "2025-04-02T12:00:45.16158",
        "modifiedAt": "2025-04-02T12:00:45.16158"
        }
```
#### 3. Http Status Massage
|         Status          |  HttpStatus   | Massage |  
|:-----------------------:|:-------------:|:-------:|
|           조회            |    200 OK     |    X    |
|    조회 결과가 존재하지 않을 경우    | 404 NOT_FOUND | "User Not Found"|
| 사용자 식별 번호 타입과 다른 요청의 경우 | 400 BAD_REQUEST |   "Invalid Type Value"|
|    사용자 식별 번호 = null     | 400 BAD_REQUEST |   "Invalid Input Value"|

## < 사용자 정보 수정 : Patch >

사용자가 자신의 사용자 이름과 email, 비밀번호를 수정할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/users/individuals

#### 2. RequestType : RequstBody

#### 3. Request Elements

|                 | RequestType  |  Type  | Required | Description  |
|:---------------:|:------------:|:------:|:--------:|:------------:|
|     로그인 여부      |   Session    |        |    O     |              |
|    userName     |  RequestBod  | String |    X     |    사용자 이름    |
|      email      | RequestBody  | String |    X     |  사용자 email   |
| presentPassword | RequestBody  | String |    O     |   현재 비밀번호    |
|   newPassword   | RequestBody  | String |    X     |    새 비밀번호    |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {
        "id": 1,
        "userName": "오애순",
        "email": "zxc@uio.com",
        "createdAt": "2025-04-02T12:00:45.16158",
        "modifiedAt": "2025-04-02T12:00:45.16158"
        },
        {//new
        "id": 1,
        "userName": "양관식",
        "email": "zxc@uio.com",
        "createdAt": "2025-04-04T22:04:56.469302",
        "modifiedAt": "2025-04-04T22:04:56.469302"
        }
]
```
#### 3. Http Status Massage
|           Status            |          HttpStatus           |             Massage             |  
|:---------------------------:|:-----------------------------:|:-------------------------------:|
|             수정              |            200 OK             |               X                 |
|    수정 요청 사용자가 존재하지 않는 경우    |         404 NOT_FOUND         |        "User Not Found"         |
|   현재 비밀번호가 null or ""인 경우   |        400 BAD_REQUEST        | "Invalid Input Value" |
|     email 형식에 맞지 않은 경우      |        400 BAD_REQUEST        | "email": "올바른 형식의 이메일 주소여야 합니다" |
| 현재 비밀 번호가 회원 정보와 일치하지 않는 경우 |        400 BAD_REQUEST        |       "Invalid Password"        |
|  각 Field와 다른 타입의 요청을 받을 경우  |        400 BAD_REQUEST        |  "Invalid Type Value"  |

## < 사용자 정보 삭제 : Delete >

사용자가 자신의 사용자 정보를 삭제 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/users/individuals

#### 2. RequestType : Path Variable, Request Param

#### 3. Request Elements

|          |  RequestType  |  Type  | Required | Description |
|:--------:|:-------------:|:------:|:--------:|:----------:|
| password | Request Param | String |    O     |    비밀 번호   |

### Response

#### Http Status Massage
|          Status          |   HttpStatus    |        Massage         |  
|:------------------------:|:---------------:|:----------------------:|
|            삭제            |     200 OK      |           X            |
|  삭제 요청 사용자가 존재하지 않는 경우   |  404 NOT_FOUND  | "요청하신 사용자를 찾을 수 없습니다." |
| 비밀 번호 파라미터 타입과 다른 요청의 경우 | 400 BAD_REQUEST |  "Invalid Type Value"  |
|  비밀 번호 = null or 공백인 경우  | 400 BAD_REQUEST | "Invalid Input Value"  |
| 비밀 번호가 회원정보랑 일치하지 않는 경우  | 400 BAD_REQUEST |   "Invalid Password"   |

## Comment API Documents

## < 댓글 생성 : Post >

댓글을 생성하여 DB에 저장합니다.

### Request

#### 1. Request URL
localhost:8080/comments

#### 2. RequestType : RequestBody(jason)

#### 3. Request Elements


| Parameter  | Request Type | Parameter or Field Type | Required | Description |
|:----------:|:------------:|:-----------------------:|:--------:|:-----------:|
|   로그인 여부   |   Session    |                         |    O     |             |
| scheduleId | Request Body |          Long           |    O     |  일정 고유 식별자  |
|  contents  | Request Body |         String          |    O     |    댓글 내용    |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
{
        "id": 1,
        "userId": 1,
        "scheduleId": 1,
        "contents": "commenttest",
        "createdAt": "2025-04-04T22:18:14.9291707"
        }
```
#### 3. Http Status Massage
|           Status           |   HttpStatus    |               Message                |  
|:--------------------------:|:---------------:|:------------------------------------:|
|           댓글 등록            |   201 CREATED   |                  X                   |
|     유저 정보를 찾을 수 없는 경우      |  404 NOT_FOUND  |           "User Not Found"           |
|     일정 정보를 찾을 수 없는 경우      |  404 NOT_FOUND  |         "Schedule Not Found"         |
| 일정 고유 식별자 = null or 공백인 경우 | 400 BAD_REQUEST |        "Invalid Input Value"         |
|   댓글 내용 = null or 공백인 경우   | 400 BAD_REQUEST |        "Invalid Input Value"         |
|     댓글 내용이 30자가 초과된 경우     | 400 BAD_REQUEST |  "contents": "크기가 0에서 30 사이여야 합니다"   |
| 각 Field와 다른 타입의 요청을 받을 경우  | 400 BAD_REQUEST |         "Invalid Type Value"         |

## < 단일 일정에 관한 전체 댓글 조회 : Get >

scheduleId로 단일 일정에 관한 전체 댓글들을 조회 할 수 있습니다.

### Request

#### Request URL
localhost:8080/comments/total/{scheduledId}

#### 2. RequestType : Path variable

#### 3. Request Elements

|            | Request Type  | Parameter Type | Required | Description |
|:----------:|:-------------:|:--------------:|:--------:|:-----------:|
|   로그인 여부   |               |                |    X     |             |
| scheduleId | Path Variable |      Long      |    O     |  일정 고유 식별자  |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {
        "id": 1,
        "userName": "양관식",
        "contents": "commenttest",
        "createdAt": "2025-04-04T22:18:14.929171",
        "modifiedAt": "2025-04-04T22:18:14.929171"
        },
        {
        "id": 2,
        "userName": "양관식",
        "contents": "commenttest2",
        "createdAt": "2025-04-04T22:18:50.315819",
        "modifiedAt": "2025-04-04T22:18:50.315819"
        },
        {
        "id": 3,
        "userName": "양관식",
        "contents": "commenttest3",
        "createdAt": "2025-04-04T22:18:53.211448",
        "modifiedAt": "2025-04-04T22:18:53.211448"
        },
        {
        "id": 4,
        "userName": "양관식",
        "contents": "commenttest4",
        "createdAt": "2025-04-04T22:18:57.207076",
        "modifiedAt": "2025-04-04T22:18:57.207076"
        }
        ]
```
#### 3. Http Status Massage
|          Status           |   HttpStatus    |       Massage        |  
|:-------------------------:|:---------------:|:--------------------:|
|            조회             |     200 OK      |          X           |
|     조회 결과가 존재하지 않을 경우     |  404 NOT_FOUND  | "Comment Not Found" |
|   shceduleId = null일 경우   | 400 BAD_REQUEST | "Invalid Input Value"|
| 댓글 고유 식별 번호 타입과 다른 요청의 경우 | 400 BAD_REQUEST |   "Invalid Type Value"|

## < 댓글 수정 : Patch >

 사용자 본인이 작성한 댓글의 내용을 수정할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/comments/individuals/{commentId}

#### 2. RequestType : Path Variable, Request Body

#### 3. Request Elements

|           |  RequestType  | Parameter Or Field Type | Required | Description |
|:---------:|:-------------:|:-----------------------:|:--------:|:-----------:|
|  로그인 여부   |    Session    |                         |    O     |             |
| commentId | PathVariable  |          Long           |    O     | 댓글 고유 식별 번호 |
| contents  | Request Param |         String          |    O     |    일정 내용    |

### Response

#### 1. Content-Type : jason

#### 2. Response Example
```xml
[
        {//new
        "id": 1,
        "scheduleId": 1,
        "userId": 1,
        "contents": "commentUpdateTest",
        "createdAt": "2025-04-04T22:18:14.929171",
        "modifiedAt": "2025-04-04T22:18:14.929171"
        }
]
```
#### 3. Http Status Massage
|               Status                |   HttpStatus    |          Massage           |  
|:-----------------------------------:|:---------------:|:--------------------------:|
|                 수정                  |     200 OK      |             X              |
|        수정 요청 댓글이 존재하지 않는 경우         |  404 NOT_FOUND  |    "Comment Not Found"     |
|      로그인 유저의 유저 정보를 찾을 수 없는 경우      |  404 NOT_FOUND  |      "User Not Found"      |
|   로그인 유저가 수정 대상 일정에 대한 인가가 없는 경우    | 400 BAD_REQUEST |   "Unauthorized Access"    |
|           댓글 식별 번호 = null | 400 BAD_REQUEST |   "Invalid Input Value"    |
|       댓글 내용 = null or 공백인 경우  |400 BAD_REQUEST | "Invalid Input Value" |
| 댓글 고유 식별 번호와 댓글 내용의 각 타입과 다른 요청의 경우 | 400 BAD_REQUEST |    "Invalid Type Value"    |
|        댓글 내용이 30자 보다 초과 된 경우        |400 BAD_REQUEST|"contents": "크기가 0에서 30 사이여야 합니다"|

## < 일정 삭제 : Delete >

댓글을 삭제 할 수 있습니다.

### Request

#### 1. Request URL
localhost:8080/schedules/individuals/{commentId}

#### 2. RequestType : PathVariable

#### 3. Request Elements

|           | RequestType  |  Type  | Required | Description |
|:---------:|:------------:|:------:|:--------:|:-----------:|
| commentId | PathVariable |  Long  |    O     | 댓글 고유 식별 번호 |
|  로그인 여부   |   Session    |        |    O     |             |

### Response

#### Http Status Massage
|             Status             |  HttpStatus   |        Massage        |  
|:------------------------------:|:-------------:|:---------------------:|
|               삭제               |    200 OK     |           X           |
|   로그인 유저의 유저 정보를 찾을 수 없는 경우    |404 NOT_FOUND|   "User Not Found"    |
| 로그인 유저가 수정 대상 일정에 대한 인가가 없는 경우 | 400 BAD_REQUEST | "Unauthorized Access" |
|      삭제 요청 댓글이 존재하지 않는 경우      | 404 NOT_FOUND |  "Comment Not Found"  |
|    작성자 식별 번호 타입과 다른 요청의 경우     | 400 BAD_REQUEST | "Invalid Type Value"  |
|        작성자 식별 번호 = null        | 400 BAD_REQUEST | "Invalid Input Value" |

## < 공통 예외>

|                          Status                          |       HttpStatus       |         Message         |  
|:--------------------------------------------------------:|:----------------------:|:-----------------------:|
| 인가 없이 인증 여부가 필요한 API에 대한 요청|    500 SERVER ERROR    | "Internel Server Error" |
| Repository(JPA) 계층 예외 |     404 NOT_FOUND      |   "Entity Not Found"    |
| 지원하지 않는 HTTP 메소드 호출 시 발생하는 예외 | 405 METHOD NOT ALLOWED |  "Method Not Allowed"   |
|  DataAccessException 처리(SQL 예외, Lock 획득 실패 등 DB 관련 예외)   |    500 SERVER ERROR    | "Internel Server Error" |

## ERD
![img.png](img.png)