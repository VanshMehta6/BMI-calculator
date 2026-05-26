# 🏋️ BMI Calculator (Java Swing GUI)

A complete BMI (Body Mass Index) calculator with a graphical user interface (GUI) built using Java Swing. The application allows users to input their personal data, calculate BMI, view health advice, and maintain a history of all calculations.

## ✨ Features

- **User-Friendly GUI** – Intuitive input form with real-time validation.
- **BMI Calculation** – Accurate BMI based on height (meters) and weight (kg).
- **Health Categories** – Underweight, Normal weight, Overweight, Obese.
- **Personalized Advice** – Health tips based on BMI result.
- **History Tracking** – Table view of all previous calculations with timestamps.
- **Data Persistence** – In-memory storage (can be extended to file/DB).
- **Input Validation** – Prevents invalid data entry.

## 📁 Project Structure

BMI/
│
├── com/
│ └── bmi/
│ ├── mains.java # Swing GUI entry point
│ ├── model/
│ │ ├── person.java # Person data & BMI logic
│ │ └── bmirecord.java # History record entity
│ ├── service/
│ │ └── bmiservice.java # Business logic & history management
│ └── util/
│ └── input.java # (Optional – not used in GUI)

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher  
  Verify installation:
  ```bash
  java -version
  javac -version

Compilation
Open a terminal in the project root directory (the folder containing the com/ folder) and run:
javac com/bmi/model/*.java com/bmi/service/*.java com/bmi/*.java

Running the Application

    java com.bmi.Main

🖥️ How to Use
1. Fill in the details:

 - Name (text)
 - Age (1–120)
 - Height in meters (0.5 – 2.5)
 - Weight in kilograms (10 – 300)

2. Click "Calculate BMI" – the result and health advice appear in the lower-left panel.

3. View History – every calculation is saved in the table at the bottom with date, name, BMI, and category.

4. Clear Fields – use the "Clear" button to reset the input form.

📊 BMI Categories
BMI Range	    Category
< 18.5	      Underweight
18.5 – 24.9	  Normal weight
25.0 – 29.9	  Overweight
≥ 30.0	      Obese

⚠️ Error Handling
- The application validates all inputs:
- Empty fields are rejected.
- Out-of-range values are prevented.
- Non‑numeric inputs are caught and reported.

🔧 Troubleshooting
Issue:	Solution
- javac: file not found	Run the command from the root directory (where com/ exists).
- GUI shows ? or weird symbols	Use chcp 65001 in Windows terminal before running, or use the ASCII version of Main.java.
- Compilation errors (encoding)	Add -encoding UTF-8 to the javac command.
- BMI always "Obese"	Check your input – ensure height is in meters (e.g., 1.75, not 175).

📦 Future Enhancements (Ideas)
- Save history to a file (CSV or JSON)
- Add weight‑loss goal tracker
- Export history report
- Chart BMI trends over time

👨‍💻 Author
- Developed as a Java Swing project demonstrating MVC‑like separation, OOP principles, and UI design.

📜 License
- This project is open source and available under the MIT License.
