package dosw.semana1.streams;

import java.util.List;

public class TallerEjercicio03 {

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
                new User(1L, "carlos", 22, true),
                new User(2L, "ana", 17, false),
                new User(3L, "beatriz", 25, true),
                new User(4L, "david", 30, false),
                new User(5L, "andres", 19, true)
        );

        List<String> sortedUsers = users.stream().filter(User::isActive).map(User::getName).map(String::toUpperCase).sorted().toList();
        System.out.println("sortedUsers = " + sortedUsers);
    }
}