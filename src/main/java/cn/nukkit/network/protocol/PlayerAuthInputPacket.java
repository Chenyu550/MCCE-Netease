package cn.nukkit.network.protocol;

import cn.nukkit.math.Vector3f;
import lombok.ToString;

@ToString
public class PlayerAuthInputPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PLAYER_AUTH_INPUT_PACKET;

    public static final int INPUT_MODE_MOUSE_KEYBOARD = 1;
    public static final int INPUT_MODE_TOUCHSCREEN = 2;
    public static final int INPUT_MODE_GAME_PAD = 3;
    public static final int INPUT_MODE_MOTION_CONTROLLER = 4;

    public static final int FLAG_ASCEND = 0;
    public static final int FLAG_DESCEND = 1;
    public static final int FLAG_NORTH_JUMP = 2;
    public static final int FLAG_JUMP_DOWN = 3;
    public static final int FLAG_SPRINT_DOWN = 4;
    public static final int FLAG_CHANGE_HEIGHT = 5;
    public static final int FLAG_JUMPING = 6;
    public static final int FLAG_AUTO_JUMPING_IN_WATER = 7;
    public static final int FLAG_SNEAKING = 8;
    public static final int FLAG_SNEAK_DOWN = 9;
    public static final int FLAG_UP = 10;
    public static final int FLAG_DOWN = 11;
    public static final int FLAG_LEFT = 12;
    public static final int FLAG_RIGHT = 13;
    public static final int FLAG_UP_LEFT = 14;
    public static final int FLAG_UP_RIGHT = 15;
    public static final int FLAG_WANT_UP = 16;
    public static final int FLAG_WANT_DOWN = 17;
    public static final int FLAG_WANT_DOWN_SLOW = 18;
    public static final int FLAG_WANT_UP_SLOW = 19;
    public static final int FLAG_SPRINTING = 20;
    public static final int FLAG_ASCEND_SCAFFOLDING = 21;
    public static final int FLAG_DESCEND_SCAFFOLDING = 22;
    public static final int FLAG_SNEAK_TOGGLE_DOWN = 23;
    public static final int FLAG_PERSIST_SNEAK = 24;
    public static final int FLAG_START_SPRINTING = 25;
    public static final int FLAG_STOP_SPRINTING = 26;
    public static final int FLAG_START_SNEAKING = 27;
    public static final int FLAG_STOP_SNEAKING = 28;
    public static final int FLAG_START_SWIMMING = 29;
    public static final int FLAG_STOP_SWIMMING = 30;
    public static final int FLAG_START_JUMPING = 31;
    public static final int FLAG_START_GLIDING = 32;
    public static final int FLAG_STOP_GLIDING = 33;

    public static final int PLAY_MODE_NORMAL = 0;
    public static final int PLAY_MODE_TEASER = 1;
    public static final int PLAY_MODE_SCREEN = 2;
    public static final int PLAY_MODE_VIEWER = 3;
    public static final int PLAY_MODE_VR = 4;
    public static final int PLAY_MODE_PLACEMENT = 5;
    public static final int PLAY_MODE_LIVING_ROOM = 6;
    public static final int PLAY_MODE_EXIT_LEVEL = 7;
    public static final int PLAY_MODE_EXIT_LEVEL_LIVING_ROOM = 8;

    public float x;
    public float y;
    public float z;
    public float pitch;
    public float yaw;
    public float headYaw;
    public float moveVecX;
    public float moveVecZ;
    public long inputFlags;
    public int inputMode;
    public int playMode;
    public float vrGazeDirectionX;
    public float vrGazeDirectionY;
    public float vrGazeDirectionZ;
    public long tick;
    public float deltaX;
    public float deltaY;
    public float deltaZ;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.pitch = this.getLFloat();
        this.yaw = this.getLFloat();
        Vector3f position = this.getVector3f();
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
        this.moveVecX = this.getLFloat();
        this.moveVecZ = this.getLFloat();
        this.headYaw = this.getLFloat();
        this.inputFlags = this.getUnsignedVarLong();
        this.inputMode = (int) this.getUnsignedVarInt();
        this.playMode = (int) this.getUnsignedVarInt();
        if (this.playMode == PLAY_MODE_VR) {
            Vector3f vrGazeDirection = this.getVector3f();
            this.vrGazeDirectionX = vrGazeDirection.x;
            this.vrGazeDirectionY = vrGazeDirection.y;
            this.vrGazeDirectionZ = vrGazeDirection.z;
        }
        this.tick = this.getUnsignedVarLong();
        Vector3f delta = this.getVector3f();
        this.deltaX = delta.x;
        this.deltaY = delta.y;
        this.deltaZ = delta.z;
    }

    @Override
    public void encode() {

    }
}
