package game;

public class UserInterface {
    private World world;

    public UserInterface(World world) {
        this.world = world;
    }

    public UserInterface() {
        this.world = new World();
    }

    public void display() {
        WorldLoader wl = new WorldLoader();
        world = wl.loadNewWorld("/gamedata.json");
        //temporary test to see if world loaded properly
        System.out.println(world);

        //TODO complete game loop
    }
}
