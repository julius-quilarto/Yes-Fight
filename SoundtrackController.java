package Soundtrack;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;


/**
 * A class that is used to control the soundtrack and audio of the game.
 * @author Alexander
 * @version 1.0
 * @music by 2ndiable
 */
public class SoundtrackController {
	
	File audioFile;
	AudioInputStream audioStream;
	Clip clip;
	
	
	/**
	 * A method that allows the user to control which song gets played.
	 * @param i Accepts an int of the audio track ID.
	 * @throws IOException Could not find audio file
	 * @throws UnsupportedAudioFileException Doesn't support this audio format.
	 */
	public void playSoundtrack(int i) throws UnsupportedAudioFileException, IOException {
		
		switch (i) {
		case 1:
			audioFile = new File("src\\Soundtrack\\Battle Theme Regular.wav");
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(audioStream);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();
			break;
		case 2:
			audioFile = new File("src/Soundtrack/Kitsune Battle Theme.wav");
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(audioStream);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();
			break;
		default:
			System.out.println("The audio you're looking for does not exist.");
			break;
		}
		
	}
	
	/**
	 * A method that allows the user to stop any track.
	 */
	public void stopPlayingSoundtrack() {
		clip.stop();
	}
	
	/**
	 * A method that allows the user to close an audio file that is currently playing.
	 */
	public void closeSoundtrack() {
		clip.close();
	}
	
	/**
	 * A method that restarts the song.
	 */
	public void restartSoundtrack() {
		clip.setMicrosecondPosition(0);
	}
}