import java.util.ArrayList;
import java.util.List;

// Общий интерфейс для файлов и папок
interface FileSystemComponent {
    void display();
    int getSize();
}

// Класс, представляющий файл
class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display() {
        System.out.println("Файл: " + name + ", Размер: " + size + " КБ");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// Класс, представляющий папку
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    // Добавление компонента в папку
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    // Удаление компонента из папки
    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Папка: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// Пример использования паттерна Компоновщик
public class Main {
    public static void main(String[] args) {
        // Создание файлов
        File file1 = new File("file1.txt", 500);
        File file2 = new File("file2.txt", 300);
        File file3 = new File("file3.txt", 700);

        // Создание папок
        Directory folder1 = new Directory("Folder1");
        Directory folder2 = new Directory("Folder2");

        // Добавление файлов в папки
        folder1.add(file1);
        folder1.add(file2);
        folder2.add(file3);

        // Создание главной папки
        Directory mainFolder = new Directory("MainFolder");

        // Добавление папок в главную папку
        mainFolder.add(folder1);
        mainFolder.add(folder2);

        // Отображение содержимого главной папки
        mainFolder.display();

        // Печать общего размера главной папки
        System.out.println("Размер главной папки: " + mainFolder.getSize() + " КБ");
    }
}
