package model;

import java.io.File;
import java.util.Arrays;

import static model.Directory.getFileExtension;

public class PrefixDirectory extends Directory {
    private int pIndex;

    public PrefixDirectory(File directory, String[] names, int pIndex) {
        super(directory, names);

        this.pIndex = pIndex;
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
     * Method: renameDirectory(string, string[], int
     * -----------------------
     * Renames all files in given directory
     *
     * @param directory   a path to directory to be renamed
     * @param names       new names of the files
     * @param prefixIndex index after the new names will be inserted
     */
    public void renameDirectory(File directory, String[] names, int prefixIndex) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (int i = 0; i < names.length && i < files.length; i++) {
            renameFile(files[i], names[i], prefixIndex);
        }
    }

    @Override
    public void renameDirectory(File directory, String[] names) {
        renameDirectory(directory, names, pIndex);
    }
}
