# SpringBoot Time Punch Demo

A simple Java SpringBoot demo that simulates a time punch system for check-ins, check-outs, and break intervals. This is merely a basic demo and not intended for production use.

## Features:

- **User Registration**
- **Punch in/out**
- **Break start/end**
- **Calculate total work time**

## Usage:

### 1. Register:

`POST http://localhost:8080/api/register`

Body:

```json
{
    "name": "Alex",
    "email": "alex@example.com",
    "password": "securepassword123"
}
```

### 2. Time Punch Actions:

For all punch actions, use:

```json
{
    "uid": "1"
}
```

- **Punch in:** `POST http://localhost:8080/api/punch/in`
- **Punch out:** `POST http://localhost:8080/api/punch/out`
- **Start Break:** `POST http://localhost:8080/api/punch/break/start`
- **End Break:** `POST http://localhost:8080/api/punch/break/end`

### 3. Retrieve Work Time:

`GET http://localhost:8080/api/time/worktime`

---

Please note that this project is intended for demonstration purposes only.
