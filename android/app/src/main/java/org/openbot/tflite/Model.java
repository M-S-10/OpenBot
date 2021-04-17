package org.openbot.tflite;

import android.util.Size;
import androidx.annotation.NonNull;

/** The model. */
public class Model {

  public enum ID {
    AUTOPILOT_F,
    DETECTOR_V1_1_0_Q,
    DETECTOR_V3_S_Q,
    YOLO_V4_TINY_F
  }

  public enum TYPE {
    AUTOPILOT,
    DETECTOR
  }

  public static final Model AUTOPILOT_F = new Model(ID.AUTOPILOT_F);
  public static final Model DETECTOR_V1_1_0_Q = new Model(ID.DETECTOR_V1_1_0_Q);
  public static final Model DETECTOR_V3_S_Q = new Model(ID.DETECTOR_V3_S_Q);
  public static final Model YOLO_V4_TINY_F = new Model(ID.YOLO_V4_TINY_F);

  public final ID id;
  public final TYPE type;
  public final String filename;

  public static Model fromId(String id) {
    switch (ID.valueOf(id)) {
      case AUTOPILOT_F:
        return AUTOPILOT_F;
      case DETECTOR_V1_1_0_Q:
        return DETECTOR_V1_1_0_Q;
      case DETECTOR_V3_S_Q:
        return DETECTOR_V3_S_Q;
      case YOLO_V4_TINY_F:
        return YOLO_V4_TINY_F;
    }
    throw new IllegalArgumentException("No model with id " + id);
  }

  private Model(ID id) {
    this.id = id;
    switch (id) {
      case AUTOPILOT_F:
        this.type = TYPE.AUTOPILOT;
        break;
      case DETECTOR_V1_1_0_Q:
      case DETECTOR_V3_S_Q:
      case YOLO_V4_TINY_F:
        this.type = TYPE.DETECTOR;
        break;
      default:
        this.type = null;
        break;
    }
    this.filename = null;
  }

  public Model(String filename) {
    this.id = ID.AUTOPILOT_F;
    this.type = TYPE.AUTOPILOT;
    this.filename = filename;
  }

  public Model(String filename, ID id, TYPE type) {
    this.id = id;
    this.type = type;
    this.filename = filename;
  }

  public static Size getCroppedImageSize(String id) {
    switch (ID.valueOf(id)) {
      case AUTOPILOT_F:
        return new Size(256, 96);
      case DETECTOR_V1_1_0_Q:
        return new Size(300, 300);
      case DETECTOR_V3_S_Q:
        return new Size(320, 320);
      case YOLO_V4_TINY_F:
        return new Size(416, 416);
    }
    throw new IllegalArgumentException("No size with id " + id);
  }

  @NonNull
  @Override
  public String toString() {
    if (filename != null) {
      return filename;
    }
    return id.name();
  }
}