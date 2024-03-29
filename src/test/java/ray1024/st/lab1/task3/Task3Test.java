package ray1024.st.lab1.task3;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Nested
    class EngineTest {
        @Test
        void louder() {
            Engine engine = new Engine();
            assertEquals(engine.getSound(), Sound.WHISTLE);
            engine.louder();
            assertEquals(engine.getSound(), Sound.NOISE);
            engine.louder();
            assertEquals(engine.getSound(), Sound.ROAR);
            assertThrows(IllegalStateException.class, engine::louder);
            engine.quieter();
            assertEquals(engine.getSound(), Sound.NOISE);
            engine.louder();
            assertEquals(engine.getSound(), Sound.ROAR);
            assertThrows(IllegalStateException.class, engine::louder);
        }

        @Test
        void quieter() {
            Engine engine = new Engine();
            assertThrows(IllegalStateException.class, engine::quieter);
            assertEquals(engine.getSound(), Sound.WHISTLE);
            engine.louder();
            assertEquals(engine.getSound(), Sound.NOISE);
            engine.quieter();
            assertEquals(engine.getSound(), Sound.WHISTLE);
            assertThrows(IllegalStateException.class, engine::quieter);
        }
    }

    @Nested
    class RocketTest {
        @Test
        void emptyRocket() {
            Rocket rocket = new Rocket(List.of());
            assertTrue(rocket.getTeam().isEmpty());
            assertEquals(rocket.getFuel(), 1000);
            assertEquals(rocket.getSpeed(), 0);
        }

        @Test
        void dash_negativeDistance() {
            Rocket rocket = new Rocket(List.of());
            assertThrows(IllegalArgumentException.class, () -> rocket.dash(-15));
        }

        @Test
        void dash_tooLong() {
            Rocket rocket = new Rocket(List.of());
            assertThrows(IllegalArgumentException.class, () -> rocket.dash(Integer.MAX_VALUE));
        }

        @Test
        void dash() {
            Rocket rocket = new Rocket(List.of());
            rocket.dash(100);
            assertEquals(rocket.getSpeed(), 0);
            assertEquals(rocket.getFuel(), 900);
        }

        @Test
        void teamMemberGetOutIntoSpace_emptyTeam() {
            Space space = new Space();
            Rocket rocket = new Rocket(List.of());
            assertThrows(IllegalStateException.class, () -> rocket.getOutTeamMemberIntoSpace(space, ""));
        }

        @Test
        void teamMemberGetOutIntoSpace_singleMember() {
            Space space = new Space();
            Rocket rocket = new Rocket(List.of(new Person("Masha", false)));
            assertEquals(rocket.getTeam().size(), 1);
            assertEquals(space.getPersons().size(), 0);
            rocket.getOutTeamMemberIntoSpace(space, "Masha");
            assertEquals(rocket.getTeam().size(), 0);
            assertEquals(space.getPersons().size(), 1);
        }

        @Test
        void teamMemberGetOutIntoSpace() {
            Space space = new Space();
            Rocket rocket = new Rocket(List.of(new Person("Masha", false), new Person("Sasha", true)));
            assertEquals(rocket.getTeam().size(), 2);
            assertEquals(space.getPersons().size(), 0);
            rocket.getOutTeamMemberIntoSpace(space, "Sasha");
            assertEquals(rocket.getTeam().size(), 1);
            assertEquals(space.getPersons().size(), 1);
            rocket.getOutTeamMemberIntoSpace(space, "Masha");
            assertEquals(rocket.getTeam().size(), 0);
            assertEquals(space.getPersons().size(), 2);
        }
    }

    @Nested
    class SpaceTest {
        @Test
        void emptySpace() {
            Space space = new Space();
            assertTrue(space.getPersons().isEmpty());
            assertTrue(space.getStars().isEmpty());
        }

        @Test
        void addPerson() {
            Space space = new Space();
            space.addPerson(new Person("Dima", true));
            space.addPerson(new Person("Masha", false));
            space.addPerson(new Person("Petya", true));
            space.addPerson(new Person("Nastya", false));
            assertEquals(space.getPersons().size(), 4);
            assertEquals(space.getPersons().stream().map(Person::getName).toList(), List.of("Dima", "Masha", "Petya", "Nastya"));
            assertEquals(space.getPersons().stream().map(Person::isMale).toList(), List.of(true, false, true, false));
        }

        @Test
        void addStar() {
            Space space = new Space();
            space.addStar(new Star(Color.CYAN));
            space.addStar(new Star(Color.RED));
            space.addStar(new Star(Color.MAGENTA));
            assertEquals(space.getStars().size(), 3);
            assertEquals(space.getStars().stream().map(Star::getColor).toList(), List.of(Color.CYAN, Color.RED, Color.MAGENTA));
        }
    }
}