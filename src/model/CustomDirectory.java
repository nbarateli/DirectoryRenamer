package model;

import java.io.File;

public class CustomDirectory extends Directory {
    private static String leadZeroes(int n) {
        return n >= 10 ? String.valueOf(n) : "0" + n;
    }

    private final int season;
    private final String series;

    public CustomDirectory(File directory, String[] names, String series, int season) {
        super(directory, names);
        this.season = season;
        this.series = series;
    }

    @Override
    public void renameDirectory(File directory, String[] names) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (int i = 0; i < names.length && i < files.length; i++) {
            renameFile(files[i], names[i], series, season, i + 1);
        }
    }

    private boolean renameFile(File file, String name, String series, int season, int episode) {
        String oldName = file.getName();
        String directory = file.getParent();
        String extension = getFileExtension(oldName);
        String newName = String.format(
                "%s\\%s.S%sE%s%s%s", directory, series, leadZeroes(season), leadZeroes(episode), name.trim().replace(' ', '.'), extension);
        return file.renameTo(new File(newName));
    }
}
