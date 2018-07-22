package model;

import java.io.File;
import java.util.Arrays;

public abstract class Directory {

    private File directory;
    private String[] names;

    /**
     * Method: getFileExtension (string)
     * ------------------------
     * Finds the extension of a file and returns it or an empty string if none present
     * e.g. if given "Directory.java", it would return ".java",
     * "t.e.x.t.txt" => ".txt", "no_file_ext" => ""
     *
     * @param name name of the file
     * @return the extension of the file or empty string if the filename doesn't contain any dots.
     */
    public static String getFileExtension(String name) {
        for (int i = name.length() - 1; i >= 0; i--)
            if (name.charAt(i) == '.')
                return name.substring(i);

        return "";
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public Directory(File directory, String[] names) {
        this.directory = directory;
        this.names = Arrays.copyOf(names, names.length);
    }

    /**
     * Method: renameDirectory(string, string[], int
     * -----------------------
     * Renames all files in given directory
     *
     * @param directory a path to directory to be renamed
     * @param names     new names of the files
     */
    public abstract void renameDirectory(File directory, String[] names);


    public String getName(int index) {
        return names[index];
    }

    public void setName(String names, int index) {
        this.names[index] = names;
    }

    public void rename() {
        renameDirectory(directory, names);
    }
}
