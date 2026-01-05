# Automation Testing OrangeHRM Menggunakan Selenium Java

## 1. Gambaran Umum Project

Project ini merupakan **Automation Testing** menggunakan **Selenium WebDriver** dengan bahasa **Java** yang dikembangkan menggunakan **IntelliJ IDEA** sebagai IDE.  
Objek pengujian yang digunakan adalah **OrangeHRM Demo Website**.

### Tujuan Project
- Mengotomatisasi pengujian fitur utama pada OrangeHRM
- Menguji skenario Login, User Management, Search System Users, dan Recruitment Vacancies
- Mengimplementasikan test case manual ke dalam automated test

### Teknologi yang Digunakan
- Java
- Selenium WebDriver
- IntelliJ IDEA
- ChromeDriver (Windows 64-bit)
- Git & GitHub

---

## 2. Setup Project (Step-by-Step)

### A. Download Tools & Dependencies

#### 1. Download Selenium Java (Stable Version)
- Cari: **Selenium Java download**
- Pilih **Stable Release**
- Download file `selenium-java-x.xx.x.zip`

#### 2. Download ChromeDriver (Windows 64-bit)
- Cari: **ChromeDriver download**
- Pastikan versi ChromeDriver sesuai dengan versi Google Chrome
- Download file `chromedriver-win64.zip`

#### 3. Extract File ZIP
Extract file berikut:
- `selenium-java-x.xx.x.zip`
- `chromedriver-win64.zip`

Pindahkan hasil extract ke:

```text
C:\Users\<username>\selenium\
```

Contoh:

```text
C:\Users\Dea\selenium\selenium-java
C:\Users\Dea\selenium\chromedriver-win64
```

## B. Install IntelliJ IDEA

1. Cari **Download IntelliJ IDEA**
2. Download **Community Edition**
3. Jalankan file `.exe`
4. Klik **Next → Next → Install** (pengaturan default)
5. Setelah proses instalasi selesai, klik **Finish**

---

## C. Membuat Project Java di IntelliJ IDEA

1. Buka **IntelliJ IDEA**
2. Klik **New Project**
3. Pilih **Java**
4. Klik **Next → Next**
5. Isi nama project:

```text
orangehrm-selenium-automation
```
5. Klik Finish

### D. Menambahkan Selenium Library (JAR)

1. Klik **File → Project Structure**
2. Pilih **Modules → Dependencies**
3. Klik **tombol +**
4. Pilih **JARs or Directories**
5. Arahkan ke folder **selenium-java**
6. Pilih semua **file .jar**
7. Klik OK → Apply → OK

## 3. Struktur Folder Project

```text
orangehrm-selenium-automation
│
├── src
│   └── test
│       ├── LoginTest.java
│       ├── UserManagementTest.java
│       ├── SearchUserTest.java
│       └── RecruitmentTest.java
│
├── README.md
└── .gitignore
```

## 4. Daftar Test Case dan Penjelasan Logic

### TC-001-01 – Login Berhasil (Credential Valid)

**Langkah Logic:**
1. Membuka browser Chrome
2. Mengakses URL OrangeHRM Demo
3. Mengisi field username dan password
4. Menekan tombol **Login**
5. Validasi berhasil masuk dashboard dan sidebar menu tampil

**Konsep Selenium:**
- `get()`
- `findElement()`
- `sendKeys()`
- `click()`
- `getCurrentUrl()`

---

### TC-001-02 – Login Gagal (Credential Tidak Valid)

**Langkah Logic:**
1. Mengisi username benar dan password salah
2. Klik tombol **Login**
3. Mengambil pesan error
4. Membandingkan dengan expected result **"Invalid credentials"**

**Konsep Penting:**
- Assertion (manual / if-else)
- Validasi pesan error

---

### TC-002-01 – Menambahkan User Berhasil (Password > 7 Karakter)

**Langkah Logic:**
1. Login sebagai Admin
2. Masuk menu **Admin → Users**
3. Klik tombol **Add**
4. Mengisi **User Role, Employee Name, Username, dan Password**
5. Klik tombol **Save**
6. Validasi notifikasi sukses dan user tampil di daftar

---

### TC-002-02 – Menambahkan User Gagal (Password < 7 Karakter)

**Langkah Logic:**
1. Masuk halaman **Add User**
2. Mengisi password kurang dari 7 karakter
3. Klik tombol **Save**
4. Validasi pesan error pada field password
5. Data user tidak tersimpan

---

### TC-003-01 s/d TC-003-04 – Pencarian System Users

**Langkah Logic Umum:**
1. Masuk menu **Admin → Users**
2. Memilih filter:
   - User Role (**Admin / ESS**)
   - Status (**Enabled / Disabled**)
3. Klik tombol **Search**
4. Validasi hasil pencarian sesuai filter

---

### TC-004-01 – Menambahkan Vacancy dengan Data Valid

**Langkah Logic:**
1. Login sebagai Admin
2. Masuk menu **Recruitment → Vacancies**
3. Klik tombol **Add**
4. Mengisi seluruh data yang dibutuhkan
5. Klik tombol **Save**
6. Validasi vacancy berhasil tersimpan

---

### TC-004-02 – Menambahkan Vacancy dengan Data Tidak Lengkap

**Langkah Logic:**
1. Mengisi form vacancy tanpa **Hiring Manager**
2. Klik tombol **Save**
3. Validasi muncul pesan error
4. Data vacancy tidak tersimpan

---

## 5. Menjalankan Project

1. Klik kanan pada file test
2. Pilih **Run**
3. Browser Chrome akan terbuka otomatis
4. Automation test berjalan sesuai skenario

**Hasil pengujian dapat dilihat melalui:**
- Console log
- Perilaku aplikasi (UI)


