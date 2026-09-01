package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio04 {

    public static class User {
        private Long id;
        private String name;
        private int age;
        private boolean active;

        public User(Long id, String name, int age, boolean active) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.active = active;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public boolean isActive() { return active; }

        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1L, "Juan", 20, true),
                new User(2L, "Sofia", 16, true),
                new User(3L, "Mateo", 18, false),
                new User(4L, "Valeria", 15, true),
                new User(5L, "Lucas", 24, true)
        );

        List<String> nombresMayoresEdad = users.stream().filter(u -> u.getAge() >= 18).map(User::getName).toList();
        System.out.println("Personas mayores de edad: " + nombresMayoresEdad);
    }
}