# 🎬 Star Wars Movie Info Console App (Java)

This is a console-based Java application that allows users to fetch and display information about **Star Wars movies** (Episodes I–VI) using the [Star Wars API (SWAPI)](https://swapi.py4e.com/). The app also saves movie details locally as JSON files.

---

## 🚀 Features

- 🧑‍💻 **Interactive Console**: Users input a movie number (1 to 6) to view details.
- 🌐 **API Integration**: Retrieves movie data from SWAPI.
- 📋 **Display**: Shows information including title, synopsis, director, release date, characters, and planets.
- 💾 **Save to File**: Stores the movie details in JSON format under the `movie_details` directory.
- ⚠️ **Input Validation & Error Handling**: Manages invalid entries and API issues gracefully.

---

## 🛠️ Technologies Used

- **Java 11+**
- `java.net.http.HttpClient` – for making HTTP requests.
- `java.util.Scanner` – for user input.
- `java.io.FileWriter` – for file writing.
- **Gson** – to parse and generate JSON.
- **Java Records** – for concise data modeling (`Movie` class).

---

## 📂 Project Structure

```bash
.
├── App.java
├── movie_details/
│   └── movie_1.json  # Sample output file

