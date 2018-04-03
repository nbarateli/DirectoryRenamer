package model;

import java.io.File;

public class Directory {

    /**
     * Method: renameDirectory(string, string[], int
     * -----------------------
     * Renames all files in given directory
     *
     * @param directory   a path to directory to be renamed
     * @param names       new names of the files
     * @param prefixIndex index after the new names will be inserted
     */
    public void renameDirectory(String directory, String[] names, int prefixIndex) {
        File[] files = new File(directory).listFiles();
        if (files == null) return;
        for (int i = 0; i < names.length && i < files.length; i++) {
            renameFile(files[i], names[i], prefixIndex);
        }
    }

    /**
     * Method: renameFile (File, string, int)
     * -----------------------
     * Renames the given file by inserting new name and its extension after
     * the given index.
     *
     * @param file the file to be renamed
     * @param name new name of the file
     * @param pi   index after the new name will be inserted
     */
    public boolean renameFile(File file, String name, int pi) {
        String oldName = file.getName();
        String directory = file.getParent();
        String extension = getFileExtension(oldName);
        String newName = directory + "\\" + oldName.substring(0, pi) + " " + name + extension;
        return file.renameTo(new File(newName));
    }

    /**
     * Method: getFileExtension (string)
     * ------------------------
     * Finds the extension of a file and returns it or an empty string if none present
     * e.g. if given "Directory.java", it would return ".java",
     * "t.e.x.t.txt" => ".txt", "no_file_ext" => ""
     *
     * @param oldName name of the file
     * @return the extension of the file or empty string if the filename doesn't contain any dots.
     */
    private String getFileExtension(String oldName) {
        for (int i = oldName.length() - 1; i >= 0; i--)
            if (oldName.charAt(i) == '.')
                return oldName.substring(i);

        return "";
    }
}
