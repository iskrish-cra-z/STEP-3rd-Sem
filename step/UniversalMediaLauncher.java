/**
 * Problem 4: Universal Media Launcher
 * Week 7 - Abstract Classes & Interfaces
 *
 * IS-A vs CAN-DO reasoning:
 * - AudioFile IS-A MediaFile: it genuinely shares fileId, format metadata, and file-based properties.
 * - Podcast CAN-DO Playable: it streams live, has no fileId, no format, no persistent file — forcing
 *   it into MediaFile would give it properties it has no use for and no way to fill meaningfully.
 */
interface Playable {
    String play();
    String play(int fromSecond); // compile-time overload
    String pause();
}

abstract class MediaFile {
    public final String fileId;
    private static int counter = 0;

    public MediaFile() {
        counter++;
        this.fileId = "MF-" + (1000 + counter);
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {
    private String title;

    public AudioFile(String title) {
        super();
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;
        return "Playing audio: " + title + " from " + minutes + ":" + String.format("%02d", seconds);
    }

    @Override
    public String pause() {
        return "Paused: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + fileId;
    }
}

// Podcast implements Playable only — no relation to MediaFile
class Podcast implements Playable {
    private String showName;
    private int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber + " of " + showName + " from second " + fromSecond;
    }

    @Override
    public String pause() {
        return "Paused: " + showName + " ep. " + episodeNumber;
    }
}

public class UniversalMediaLauncher {
    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            if (item != null) {
                System.out.println(item.play());
            }
        }
    }

    public static void main(String[] args) {
        AudioFile a = new AudioFile("Morning Jazz");
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        Podcast p = new Podcast("Tech Talk", 12);
        System.out.println(p.play());

        // Upcasting: AudioFile stored as its Playable interface type
        Playable ref = a;
        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}
