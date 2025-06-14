# API Automation with Rest Assured
This project is a small suite of automated API tests written in Java using **Rest Assured**, **TestNG**, and **Extent Reports**. It demonstrates a basic **user management workflow** using the public API provided by [Reqres.in](https://reqres.in/).

---

## 📌 Scenarios Covered

1. **Create User** – `POST /users
2. **Update User** – `PUT /users/{id}
3. **Get User** – `GET /users/{id}
4. **Delete User** – `DELETE /users/{id}
5. **Get User (Verify Deletion)** – `GET /users/{id}

---

## 🧰 Tech Stack

- **Java 21**
- **Rest Assured**
- **TestNG**
- **Extent Reports** (for HTML reporting)
- **Maven**

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/sobhy97/AAIB_Technical_Task
2. Open the task through IntelliJ IDEA
3. open testng.xml file
4. right click and select Run testng.xml file

---

✅ Best Practices Used
1-Reusable ExtentReports via singleton pattern

2-TestNG annotations (@BeforeSuite, @Test, @AfterSuite)

3-Parameterized base URI

4-Data extraction and reuse between API calls

5-Clean separation of logic and utilities

---

📞 Contact
For questions or improvements, feel free to reach out


