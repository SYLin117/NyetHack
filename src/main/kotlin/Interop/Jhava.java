package Interop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//@file:JvmName("Hero")

public class Jhava {
    private int hitPoints = 52489112;
    private String greeting = "BLARGH";

    public int getHitPoints() {
        return hitPoints;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @NotNull
    public String utterGreeting() {
        return greeting;
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    public static void main(String[] args) {
        // 在java中呼叫kotlin的函數
        System.out.println(HeroKt.makeProclamation());

        System.out.println("Spells: ");
        Spellbook spellbook = new Spellbook();
        for (String spell : spellbook.spells) {
            System.out.println(spell);
        }
        System.out.println("Max spell count:"+
                Spellbook.MAX_SPELL_COUNT);

        Spellbook.getSpellbookGreeting();

    }

    public void offerFood() {
        HeroKt.handOverFood("pizza");
    }
}
