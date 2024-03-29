package ray1024.st.lab1.task3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Space {
    private final List<Person> persons = new ArrayList<>();
    private final List<Star> stars = new ArrayList<>();

    public void addPerson(Person person) {
        if (persons.contains(person)) throw new IllegalArgumentException("This person's already floating in the space");
        persons.add(person);
    }

    public void addStar(Star star) {
        if (stars.contains(star))
            throw new IllegalArgumentException("This person's already floating in the space");
        stars.add(star);
    }
}
