# 1.0 Documentation (not completed yet...)
## 1.1 Introduction
- **Project Name :** CCSP or Centralized Cloud Storage Projectt
  
- **Description**: A Centralized cloud storage application that allows users to upload, download, and manage their files. initially hosting will be locally without using any server-less technologies like AWS , Azure and Google, instead will be hosted on a raspberry Pi device with NAS disks.

## 1.2 Project Goals
A list below shows what am personally trying to achieve while building this project:
- access the CCSP from internet (wide open and not locally with home modem)
- Upload and download files from different media types:
	- initially:
		- files from type: `.txt | .pdf | .png | .jpeg` 
	- Future + including initially types:
		 - file from type: `.docx | .zip`
		 - files from (has limitations): `.mp4 | .mp3`

## 1.3 Core Features
### 1.3.1 Functional Requirements:
- (FR-0-01) **Upload Files**: The system shall support uploading files from local user device to cloud storage
- (FR-0-02) **Download Files**: The system shall support downloading files from cloud storage to local user device.
- (FR-0-03) **Deletion Files** The system shall support deleting personal files for user from cloud storage
- (FR-0-04) **Share drive** : the system shall allow users  to share the vault via email listing or generating links
- (FR-0-05) **View Folder**: the system shall allow users view list of uploaded folders and files on the cloud web page
- (FR-0-06) **View vault Capacity**: the system shall show the user their own used and empty capacity of the vault
- (FR-0-07) **Sign-in and Sign-up**:The system shall provide a user registration and login page  
- (FR-0-08) **Custom Domain**: The system shall be accessible via custom domain name with dynamic DNS configuration for stable access
### 1.3.2 Non-Functional Requirements
- (NFR-0-01) **Security Method**: The system shall use a method of security ==(to be discussed later)==
- (NFR-0-02) **Domain Accessibility**: The system shall be accessible through a memorable domain name (e.g., mystorage.mydomain.com) instead of IP address
- (NFR-0-03) **Dynamic DNS**: The system shall use dynamic DNS service to maintain domain accessibility despite changing home IP addresses
  
  discuss what to add more here....

## 1.4 Future Features
### 1.4.1 Functional Requirements:
- (FR-1-0) **Dedicated Server**: The system shall be hosted on raspberry pi 5.
- (FR-1-02) **Containerization**: The system shall use Docker for deployment and updates

need to be disscussed for more things
### 1.4.2 Non-Functional Requirements:
- (NFR-1-1) **Enhanced Storage**: The system shall have at least 1TB.


## 1.5 Constraints and Assumptions
### Constraints (==to be discussed later==)
1. buying a custom domain with yearly payment  in 10-20$
2. exposing server to internet requires cloudflare tunneling
   
## 1.6 Technology Stack
### Back-end:
- **Back-End language** :Java
- **Framework** : SpringBoot
- **Database** : SQLite
- **Authentications** : (==to be discussed later==) JWT
- **File Upload** : (==to be discussed later==) multer
- **security** : (==to be discussed later==)
### Front-end:
- **Framework** : React.js 
### Development tools:
- **Version control**: Git & GitHub
- **Code Editor**: VSCode
- **Containerization**: Docker
#### **Testing tools**:
- **Functional (Top Priority)**
	- **API Testing:** Postman
	- **Unit Testing:** JUnit (JUnit 5)
	- **Integration Testing:** Mockito 
	- **Database Testing:** H2 (In-memory database), Oracle DB

- **Functional (Less Priority)**
	- **Regression Testing:** Maven (`mvn test`) and JUnit

- **Non-Functional (Most Priority)**
	- **Security Testing:** 
		- SpotBugs (Static Analysis) 
		- BCrypt (Password Hashing) 
	- **Performance Testing (Load & Stress):** JMeter
	  
- **Non-Functional (Less Priority)**
	- **Compatibility Testing:** Selenium Grid
	- **Automated UI Testing:** Selenium WebDriver / Selenium IDE    
	- **Defect Tracking:** Todoist
	- **Continuous Integration (CI):** Jenkins and GitHub Actions
### Production Environment:
- **Protocol** : HTTP \ HTTPS
-  **WebServer**: (==to be discussed later==)
- **Operating System**: Debian
- **Hardware**: 
	- Huawei D14 (my laptop)
	- Raspberry Pi 5 
- **Storage** :
	- Raspberry Pi 5 (128GB) 


## 1.7 System Limitations
# 2.0 Design (not reached yet...)

## 2.1 System Architecture  