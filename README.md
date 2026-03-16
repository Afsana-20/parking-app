# 🚗 ParkSmart - Vehicle Parking Management System

## 📌 Project Overview
A professional *Vehicle Parking Management System* built as part of the *Cloud Essentials* subject project. The system manages vehicle entry, exit, slot allocation, and billing — deployed on cloud using *Render.com*.

---

## 🎯 Features
| Feature | Description |
|---|---|
| 📥 Vehicle Entry | Register vehicle & auto-assign parking slot |
| 📤 Vehicle Exit | Process exit & generate payment bill |
| 🧾 Bill Receipt | Printable receipt with duration & charges |
| 🅿️ Slot View | Visual map of all 25 slots across 3 floors |
| 📋 All Records | Complete history of all parking sessions |
| 📊 Dashboard | Real-time stats & occupancy tracking |

---

## 🚘 Vehicle Types & Rates
| Type | Slots | Floor | Rate |
|---|---|---|---|
| 🏍️ 2-Wheeler | TW-01 to TW-10 | Floor 1 | ₹10/hr |
| 🚗 4-Wheeler | FW-01 to FW-10 | Floor 2 | ₹20/hr |
| 🚛 Heavy Vehicle | HV-01 to HV-05 | Floor 3 | ₹40/hr |

---

## 🛠️ Tech Stack
| Layer | Technology |
|---|---|
| *Language* | Java 21 |
| *Framework* | Spring Boot 3.2 |
| *Frontend* | Thymeleaf + HTML + CSS |
| *Database (Local)* | H2 In-Memory |
| *Database (Cloud)* | PostgreSQL |
| *Cloud Platform* | Render.com (Free) |
| *Build Tool* | Maven |

---

## ☁️ Cloud Concepts Demonstrated
| Concept | Implementation |
|---|---|
| *Compute* | Render Web Service runs Spring Boot app |
| *Managed Database* | Render PostgreSQL — no setup needed |
| *Environment Variables* | Secure config — no hardcoded passwords |
| *CI/CD Pipeline* | GitHub push → Render auto-redeploys |
| *Public Networking* | HTTPS URL accessible globally |
| *Multi-tier Architecture* | Frontend + Backend + Database layers |
| *Containerization* | Docker packages the app for deployment |

---

## 📁 Project Structure

parking-app/
├── src/main/java/com/cloudessentials/parking_app/
│   ├── controller/
│   │   └── ParkingController.java
│   ├── model/
│   │   ├── VehicleType.java
│   │   ├── ParkingSlot.java
│   │   └── ParkingRecord.java
│   ├── repository/
│   │   ├── ParkingSlotRepository.java
│   │   └── ParkingRecordRepository.java
│   ├── service/
│   │   ├── ParkingService.java
│   │   └── DataInitializer.java
│   └── ParkingAppApplication.java
├── src/main/resources/
│   ├── templates/
│   │   ├── dashboard.html
│   │   ├── entry.html
│   │   ├── exit.html
│   │   ├── slots.html
│   │   └── records.html
│   ├── application.properties
│   └── application-prod.properties
└── pom.xml


---

## 🚀 Run Locally
bash
# Step 1 - Go to project folder
cd parking-app

# Step 2 - Build the project
mvn clean package -DskipTests

# Step 3 - Run the app
java -jar target/parking-app-0.0.1-SNAPSHOT.jar

# Step 4 - Open browser
http://localhost:8080


---

## ☁️ Deploy on Render.com

### Step 1 - Create PostgreSQL Database
- Go to Render.com → New → PostgreSQL
- Copy Internal Database URL

### Step 2 - Create Web Service
- Go to Render.com → New → Web Service
- Connect GitHub repo
- Set Build Command:

mvn clean package -DskipTests

- Set Start Command:

java -Dspring.profiles.active=prod -jar target/parking-app-0.0.1-SNAPSHOT.jar


### Step 3 - Add Environment Variables

DATABASE_URL = <PostgreSQL Internal URL>
SPRING_PROFILES_ACTIVE = prod


---

## 📊 System Flow

User → Browser
         ↓
   Spring Boot App (Render)
         ↓
   PostgreSQL Database (Render)
         ↓
   Response back to User


---

## 👨‍💻 Project Details
- *Subject:* Cloud Essentials
- *Platform:* Render.com (Free Tier)
- *Live URL:* https://parksmart.onrender.com
