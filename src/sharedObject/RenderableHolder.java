package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image tile1;
	public static Image tile2;
	public static Image tile3;

	// main
	public static Image wallpaper;
	public static Image logo1;
	public static Image gameEnd;
	public static Image pauseImg;

	// player
	public static Image playerNormalLeftImg;
	public static Image playerNormalRightImg;
	public static Image playerUpImg;
	public static Image playerUpRightImg;
	public static Image playerLeftImg;
	public static Image playerRightImg;
	public static Image playerDownImg;
	public static Image playerDownRightImg;
	public static Image playerDownLeftImg;

	// weapon1
	public static Image shotgunUp;
	public static Image shotgunLeft;
	public static Image shotgunDown;
	public static Image shotgunRight;
	public static Image shotgunLeftUp;
	public static Image shotgunLeftDown;
	public static Image shotgunRightDown;
	public static Image shotgunRightUp;

	// weapon2
	public static Image uziUp;
	public static Image uziLeft;
	public static Image uziDown;
	public static Image uziRight;
	public static Image uziLeftUp;
	public static Image uziLeftDown;
	public static Image uziRightDown;
	public static Image uziRightUp;

	// weapon3
	public static Image busterRifleUp;
	public static Image busterRifleLeft;
	public static Image busterRifleDown;
	public static Image busterRifleRight;
	public static Image busterRifleLeftUp;
	public static Image busterRifleLeftDown;
	public static Image busterRifleRightDown;
	public static Image busterRifleRightUp;

	// zombie1
	public static Image zombie1UpImg;
	public static Image zombie1UpRightImg;
	public static Image zombie1LeftImg;
	public static Image zombie1RightImg;
	public static Image zombie1DownImg;
	public static Image zombie1DownRightImg;
	public static Image zombie1DownLeftImg;

	// zombie2
	public static Image zombie2UpImg;
	public static Image zombie2UpRightImg;
	public static Image zombie2LeftImg;
	public static Image zombie2RightImg;
	public static Image zombie2DownImg;
	public static Image zombie2DownRightImg;
	public static Image zombie2DownLeftImg;

	// bossZombie
	public static Image bossUpImg;
	public static Image bossUpRightImg;
	public static Image bossLeftImg;
	public static Image bossRightImg;
	public static Image bossDownImg;
	public static Image bossDownRightImg;
	public static Image bossDownLeftImg;

	// lootbox
	public static Image box;
	public static AudioClip lootSound;

	// wall
	public static Image wallImg0000;
	public static Image wallImg0002;
	public static Image wallImg0004;
	public static Image wallImg0005;
	public static Image wallImg0010;
	public static Image wallImg0011;
	public static Image wallImg0013;
	public static Image wallImg0014;
	public static Image wallImg0015;
	public static Image wallImg0016;
	public static Image wallImg0017;
	public static Image wallImg0026;
	public static Image wallImg0028;
	public static Image wallImg0040;
	public static Image wallImg0057;
	public static Image wallImg0059;
	public static Image wallImg0132;

	public static Image bossAmmoImg2;
	public static Image bossAmmoImg3;
	public static Image pistolAmmoImg0;
	public static Image pistolAmmoImg45;
	public static Image pistolAmmoImg90;
	public static Image pistolAmmoImg135;
	public static Image pistolAmmoImg180;
	public static Image pistolAmmoImg225;
	public static Image pistolAmmoImg270;
	public static Image pistolAmmoImg315;

	// gunSound
	public static AudioClip pistolSound;
	public static AudioClip shotgunSound;
	public static AudioClip uziSound;
	public static AudioClip railgunSound;

	public static AudioClip textClickedSound;
	public static AudioClip endGame;
	public static AudioClip boom;
	public static AudioClip bossBoom;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		tile1 = new Image(ClassLoader.getSystemResource("Tiles/tile_0048.png").toString());
		tile2 = new Image(ClassLoader.getSystemResource("Tiles/tile_0049.png").toString());
		tile3 = new Image(ClassLoader.getSystemResource("Tiles/tile_0042.png").toString());

		// main
		wallpaper = new Image(ClassLoader.getSystemResource("main/wallpaper.jpeg").toString());
		logo1 = new Image(ClassLoader.getSystemResource("main/logo1.png").toString());
		gameEnd = new Image(ClassLoader.getSystemResource("main/gameendimg.png").toString());
		pauseImg = new Image(ClassLoader.getSystemResource("main/pause.png").toString());

		// player
		playerNormalLeftImg = new Image(ClassLoader.getSystemResource("gundam/strike_normalleft.PNG").toString());
		playerNormalRightImg = new Image(ClassLoader.getSystemResource("gundam/strike_normalright.png").toString());
		playerUpImg = new Image(ClassLoader.getSystemResource("gundam/strike_up.PNG").toString());
		playerUpRightImg = new Image(ClassLoader.getSystemResource("gundam/strike_upright.png").toString());
		playerLeftImg = new Image(ClassLoader.getSystemResource("gundam/strike_left.PNG").toString());
		playerRightImg = new Image(ClassLoader.getSystemResource("gundam/strike_right.png").toString());
		playerDownImg = new Image(ClassLoader.getSystemResource("gundam/strike_down.PNG").toString());
		playerDownLeftImg = new Image(ClassLoader.getSystemResource("gundam/strike_downleft.PNG").toString());
		playerDownRightImg = new Image(ClassLoader.getSystemResource("gundam/strike_downright.png").toString());

		// weapon1
		shotgunUp = new Image(ClassLoader.getSystemResource("weapon/shotgun_up.png").toString());
		shotgunLeft = new Image(ClassLoader.getSystemResource("weapon/shotgun_left.png").toString());
		shotgunDown = new Image(ClassLoader.getSystemResource("weapon/shotgun_down.png").toString());
		shotgunRight = new Image(ClassLoader.getSystemResource("weapon/shotgun_right.png").toString());
		shotgunLeftUp = new Image(ClassLoader.getSystemResource("weapon/shotgun_upleft.png").toString());
		shotgunLeftDown = new Image(ClassLoader.getSystemResource("weapon/shotgun_downleft.png").toString());
		shotgunRightUp = new Image(ClassLoader.getSystemResource("weapon/shotgun_upright.png").toString());
		shotgunRightDown = new Image(ClassLoader.getSystemResource("weapon/shotgun_downright.png").toString());

		// weapon2
		uziUp = new Image(ClassLoader.getSystemResource("weapon/uzi_up.png").toString());
		uziLeft = new Image(ClassLoader.getSystemResource("weapon/uzi_left.png").toString());
		uziDown = new Image(ClassLoader.getSystemResource("weapon/uzi_down.png").toString());
		uziRight = new Image(ClassLoader.getSystemResource("weapon/uzi_right.png").toString());
		uziLeftUp = new Image(ClassLoader.getSystemResource("weapon/uzi_upleft.png").toString());
		uziLeftDown = new Image(ClassLoader.getSystemResource("weapon/uzi_downleft.png").toString());
		uziRightUp = new Image(ClassLoader.getSystemResource("weapon/uzi_upright.png").toString());
		uziRightDown = new Image(ClassLoader.getSystemResource("weapon/uzi_downright.png").toString());

		// weapon3
		busterRifleUp = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_up.png").toString());
		busterRifleLeft = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_left.png").toString());
		busterRifleDown = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_down.png").toString());
		busterRifleRight = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_right.png").toString());
		busterRifleLeftUp = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_leftup.png").toString());
		busterRifleLeftDown = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_leftdown.png").toString());
		busterRifleRightUp = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_rightup.png").toString());
		busterRifleRightDown = new Image(ClassLoader.getSystemResource("weapon/buster_rifle_rightdown.png").toString());

		// zombie1
		zombie1UpImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_up.PNG").toString());
		zombie1UpRightImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_upright.png").toString());
		zombie1LeftImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_left.PNG").toString());
		zombie1RightImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_right.png").toString());
		zombie1DownImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_down.PNG").toString());
		zombie1DownRightImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_downright.png").toString());
		zombie1DownLeftImg = new Image(ClassLoader.getSystemResource("zaku/zaku1_downleft.PNG").toString());

		// zombie2
		zombie2UpImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_up.PNG").toString());
		zombie2UpRightImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_upright.png").toString());
		zombie2LeftImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_left.PNG").toString());
		zombie2RightImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_right.png").toString());
		zombie2DownImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_down.PNG").toString());
		zombie2DownRightImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_downright.png").toString());
		zombie2DownLeftImg = new Image(ClassLoader.getSystemResource("zaku/zaku2_downleft.PNG").toString());

		// bossZombie
		bossUpImg = new Image(ClassLoader.getSystemResource("gundam/epyon_up.PNG").toString());
		bossUpRightImg = new Image(ClassLoader.getSystemResource("gundam/epyon_upright.png").toString());
		bossLeftImg = new Image(ClassLoader.getSystemResource("gundam/epyon_left.PNG").toString());
		bossRightImg = new Image(ClassLoader.getSystemResource("gundam/epyon_right.png").toString());
		bossDownImg = new Image(ClassLoader.getSystemResource("gundam/epyon_down.PNG").toString());
		bossDownRightImg = new Image(ClassLoader.getSystemResource("gundam/epyon_downright.png").toString());
		bossDownLeftImg = new Image(ClassLoader.getSystemResource("gundam/epyon_downleft.PNG").toString());

		// lootbox
		box = new Image(ClassLoader.getSystemResource("lootbox/haro.PNG").toString());
		lootSound = new AudioClip(ClassLoader.getSystemResource("audio/lootCollect.wav").toString());

		// wall
		wallImg0000 = new Image(ClassLoader.getSystemResource("Tiles/tile_0000.png").toString());
		wallImg0002 = new Image(ClassLoader.getSystemResource("Tiles/tile_0002.png").toString());
		wallImg0004 = new Image(ClassLoader.getSystemResource("Tiles/tile_0004.png").toString());
		wallImg0005 = new Image(ClassLoader.getSystemResource("Tiles/tile_0005.png").toString());
		wallImg0010 = new Image(ClassLoader.getSystemResource("Tiles/tile_0010.png").toString());
		wallImg0011 = new Image(ClassLoader.getSystemResource("Tiles/tile_0011.png").toString());
		wallImg0013 = new Image(ClassLoader.getSystemResource("Tiles/tile_0013.png").toString());
		wallImg0014 = new Image(ClassLoader.getSystemResource("Tiles/tile_0014.png").toString());
		wallImg0015 = new Image(ClassLoader.getSystemResource("Tiles/tile_0015.png").toString());
		wallImg0016 = new Image(ClassLoader.getSystemResource("Tiles/tile_0016.png").toString());
		wallImg0017 = new Image(ClassLoader.getSystemResource("Tiles/tile_0017.png").toString());
		wallImg0026 = new Image(ClassLoader.getSystemResource("Tiles/tile_0026.png").toString());
		wallImg0028 = new Image(ClassLoader.getSystemResource("Tiles/tile_0028.png").toString());
		wallImg0040 = new Image(ClassLoader.getSystemResource("Tiles/tile_0040.png").toString());
		wallImg0057 = new Image(ClassLoader.getSystemResource("Tiles/tile_0057.png").toString());
		wallImg0059 = new Image(ClassLoader.getSystemResource("Tiles/tile_0059.png").toString());
		wallImg0132 = new Image(ClassLoader.getSystemResource("Tiles/tile_0132.png").toString());

		bossAmmoImg2 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_2_bossAmmo.png").toString());
		bossAmmoImg3 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_3_bossAmmo.png").toString());
		pistolAmmoImg0 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_0_pistol.png").toString());
		pistolAmmoImg45 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_45_pistol.png").toString());
		pistolAmmoImg90 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_90_pistol.png").toString());
		pistolAmmoImg135 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_135_pistol.png").toString());
		pistolAmmoImg180 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_180_pistol.png").toString());
		pistolAmmoImg225 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_225_pistol.png").toString());
		pistolAmmoImg270 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_270_pistol.png").toString());
		pistolAmmoImg315 = new Image(ClassLoader.getSystemResource("ammo/boxhead_ammo_315_pistol.png").toString());

		// gunSound
		pistolSound = new AudioClip(ClassLoader.getSystemResource("audio/pistol_sound.wav").toString());
		shotgunSound = new AudioClip(ClassLoader.getSystemResource("audio/shotgun_sound.wav").toString());
		uziSound = new AudioClip(ClassLoader.getSystemResource("audio/uzi_sound.wav").toString());
		railgunSound = new AudioClip(ClassLoader.getSystemResource("audio/railgun_sound.wav").toString());

		textClickedSound = new AudioClip(ClassLoader.getSystemResource("audio/playButtonClicked.wav").toString());
		endGame = new AudioClip(ClassLoader.getSystemResource("audio/game_end.wav").toString());
		bossBoom = new AudioClip(ClassLoader.getSystemResource("audio/bossBoom.wav").toString());
		boom = new AudioClip(ClassLoader.getSystemResource("audio/boom.wav").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void clear() {
		entities = new ArrayList<IRenderable>();
	}

	public void update() {
		Collections.sort(entities, comparator);
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed() || !entities.get(i).isVisible())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
