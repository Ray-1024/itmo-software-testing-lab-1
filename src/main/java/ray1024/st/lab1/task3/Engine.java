package ray1024.st.lab1.task3;

import lombok.Getter;

@Getter
public class Engine {
    private Sound sound = Sound.WHISTLE;

    public void louder() {
        if (sound.ordinal() + 1 >= Sound.values().length) throw new IllegalStateException("Can't be louder");
        this.sound = Sound.values()[sound.ordinal() + 1];
    }

    public void quieter() {
        if (sound.ordinal() - 1 < 0) throw new IllegalStateException("Can't be quieter");
        this.sound = Sound.values()[sound.ordinal() - 1];
    }
}
