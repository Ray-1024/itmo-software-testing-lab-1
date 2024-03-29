package ray1024.st.lab1.task3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Rocket {
    private final List<Person> team;
    private final Engine engine = new Engine();
    private int fuel = 1000;
    private int speed = 0;

    public Rocket(List<Person> team) {
        this.team = new ArrayList<>(team);
    }

    private void speedUp() {
        engine.louder();
        speed += 10;
    }

    private void speedDown() {
        engine.quieter();
        speed -= 10;
    }

    public void dash(int distance) {
        if (distance < 0) throw new IllegalArgumentException("Distance can't be negative");
        if (distance > fuel) throw new IllegalArgumentException("Hasn't enough fuel");
        speedUp();
        fuel -= distance;
        speedDown();
    }

    public void getOutTeamMemberIntoSpace(Space space, String teamMemberName) {
        Person teamMember = team.stream().filter(person -> person.getName().equals(teamMemberName)).findFirst().orElseThrow(() -> new IllegalStateException("This person isn't a team member"));
        space.addPerson(teamMember);
        team.remove(teamMember);
    }
}
