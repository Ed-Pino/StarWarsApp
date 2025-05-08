import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;

public class App {

    private static final String BASE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=";
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎬 Bienvenido a la App de Películas de Star Wars");
        System.out.println("Ingresa el número de película (1 a 6) o escribe 'salir' para terminar.");

        while (true) {
            System.out.print("Número de película: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("salir")) {
                System.out.println("👋 ¡Gracias por usar la aplicación!");
                break;
            }

            try {
                int movieId = Integer.parseInt(input);
                if (movieId < 1 || movieId > 6) {
                    System.out.println("⚠️ Ingresa un número entre 1 y 6.");
                    continue;
                }

                String url = BASE_URL + movieId + "/";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://swapi.py4e.com/api/films/1/"))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    Movie movie = gson.fromJson(response.body(), Movie.class);
                    displayMovieDetails(movie);
                    saveMovieToFile(movie, movieId);
                } else {
                    System.out.println("🚫 Error: Película no encontrada (HTTP " + response.statusCode() + ")");
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingresa un número válido entre 1 y 6.");
            } catch (IOException | InterruptedException e) {
                System.out.println("🚨 Error al conectarse a la API: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMovieDetails(Movie movie) {
        System.out.println("\n🎬 Título: " + movie.title());
        System.out.println("📌 Episodio: " + movie.episode_id());
        System.out.println("📜 Sinopsis: " + movie.opening_crawl());
        System.out.println("🎥 Director: " + movie.director());
        System.out.println("🎬 Productor: " + movie.producer());
        System.out.println("📅 Fecha de estreno: " + movie.release_date());
        System.out.println("👥 Personajes: " + movie.characters().size() + " referencias");
        System.out.println("🪐 Planetas: " + movie.planets().size() + " referencias\n");
    }

    private static void saveMovieToFile(Movie movie, int movieId) {
        String fileName = "movie_details/movie_" + movieId + ".json";

        try {
            String json = gson.toJson(movie);
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
            System.out.println("💾 Película guardada en: " + fileName);
        } catch (IOException e) {
            System.out.println("🚨 Error al guardar el archivo: " + e.getMessage());
        }
    }

    public record Movie(
            String title,
            String episode_id,
            String opening_crawl,
            String director,
            String producer,
            String release_date,
            List<String> characters,
            List<String> planets
    ) {}
}