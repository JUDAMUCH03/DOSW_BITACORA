package dosw.semana4.PatronesCombinados;

import java.util.ArrayList;
import java.util.List;

record User(String name, String favoriteGenre, List<String> history) {}
record Content(String title, String genre) {}

// Strategy: Algoritmos de recomendación
interface RecommendationAlgorithm {
    List<Content> recommend(User user);
}

class GenreStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        System.out.println("[Strategy: Género] Filtrando títulos por " + user.favoriteGenre());
        return List.of(new Content("Interestelar", "Sci-Fi"), new Content("Matrix", "Sci-Fi"));
    }
}

class HistoryStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        System.out.println("[Strategy: Historial] Buscando contenido similar a: " + user.history());
        return List.of(new Content("Blade Runner 2049", "Sci-Fi"), new Content("Dune", "Sci-Fi"));
    }
}

class PopularityStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        System.out.println("[Strategy: Popularidad] Cargando el Top Global");
        return List.of(new Content("Stranger Things", "Drama"), new Content("The Boys", "Acción"));
    }
}

// Observer: Suscriptores que reaccionan al cambio de preferencias
interface PreferenceObserver {
    void onPreferenceChanged(User user);
}

class HomePageComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("📺 [HomePageComponent] Re-renderizando catálogo de portada para " + user.name());
    }
}

class SuggestedListComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("📑 [SuggestedListComponent] Actualizando lista de recomendados para " + user.name());
    }
}

// Subject / Motor
class RecommendationEngine {
    private RecommendationAlgorithm strategy;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    public RecommendationEngine(RecommendationAlgorithm strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RecommendationAlgorithm strategy) {
        this.strategy = strategy;
    }

    public void addObserver(PreferenceObserver observer) {
        observers.add(observer);
    }

    public void changeUserPreferences(User user) {
        System.out.println("\n>>> El usuario " + user.name() + " actualizó sus preferencias <<<");
        List<Content> recommendations = strategy.recommend(user);
        System.out.println("Recomendaciones generadas: " + recommendations);
        for (PreferenceObserver obs : observers) {
            obs.onPreferenceChanged(user);
        }
    }
}

public class Ejercicio06 {
    public static void main(String[] args) {
        User user = new User("Juan", "Sci-Fi", List.of("Cyberpunk"));

        RecommendationEngine engine = new RecommendationEngine(new GenreStrategy());
        engine.addObserver(new HomePageComponent());
        engine.addObserver(new SuggestedListComponent());

        engine.changeUserPreferences(user);

        // Cambio de estrategia en runtime
        engine.setStrategy(new PopularityStrategy());
        engine.changeUserPreferences(user);
    }
}