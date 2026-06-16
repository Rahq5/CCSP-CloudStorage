
# 1.0 Documentation (not completed yet...)
## 1.1 Introduction
- **Project Name :** CCSP or Centralized Cloud Storage Project
  
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
- (FR-01) Upload Files
- (FR-02) Download Files
- (FR-03) Deletion Files
- (FR-04) Share drive ==(to be discussed later)==
- (FR-05) view list of uploaded folders and files on the cloud web page
- (FR-06) view files storage capacity
- (FR-07) The system shall provide a user registration and login page  
### 1.3.2 Non-Functional Requirements
- (NFR-01) The system shall use a method of security ==(to be discussed later)==
  
  discuss what to add more here....

## 1.4 Future Features
### 1.4.1 Functional Requirements:
- (FR-01) **Dedicated Server**: The system shall be hosted on raspberry pi 5.
- (FR-02) **Containerization**: The system shall use Docker for deployment and updates

need to be disscussed for more things
### 1.4.2 Non-Functional Requirements:
- (NFR- 0) **Enhanced Storage**: The system shall have at least 1TB.


## 1.5 Constraints and Assumptions
....
## 1.6 Technology Stack
### Back-end:
- **Back-End language** : Node.js
- **Framework** : Express.js
- **Database** : SQLite
- **Authentications** : (==to be discussed later==) JWT
- **File Upload** : (==to be discussed later==) multer
- **security** : (==to be discussed later==)
### Front-end:
- **Language** : React.js 
### Development tools:
- **Version control**: Git & GitHub
- **Code Editor**: VSCode
- **Containerization**: Docker
- **Testing tools**:
	- for **API**: Postman
	- for others: (==to be discussed later==)

### Production Environment:
- **Protocol** : HTTP \ HTTPS
-  **WebServer**: (==to be discussed later==)
- **Operating System**: Debian
- **Hardware**: 
	- initially: local Huawei D14 (my laptop)
	- Future: Raspberry Pi 5 
- **Storage** :
	- initially: local Huawei D14 storage (my laptop disk)
	- Future: Raspberry Pi 5 


## 1.7 System Limitations
# 2.0 Design (not reached yet...)

## 2.1 System Architecture  