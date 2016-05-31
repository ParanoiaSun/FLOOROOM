package audio;

import java.applet.Applet;                                                                                                                                                                                                                        
import java.applet.AudioClip;
/**
*
*/

public class AudioPlayer1 {
  AudioClip clip = null;
//  static boolean play=true;
  public AudioPlayer1(String name) {
     try {
       setAudioClip(Applet.newAudioClip( (new java.io.File(name)).toURL()));
     }
     catch (Exception ex) {
       ex.printStackTrace();
     }
 }

 public AudioClip getAudioClip() {
   return this.clip;
 }

 public void setAudioClip(AudioClip clip) {
   this.clip = clip;
 }
 public void play() {
   if (getAudioClip() != null) {
     getAudioClip().play();
   }
 }

 void loop() {
   if (getAudioClip() != null) {
     getAudioClip().loop();
   }
 }

public void stop() {
    if (getAudioClip() != null) {
     getAudioClip().stop();
   }
 }
}