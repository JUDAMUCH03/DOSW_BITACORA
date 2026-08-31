package dosw.semana4.PatronesCombinados;

import java.util.ArrayList;
import java.util.List;

// Componente
interface Image {
    String render();
    Image getWrapped();
}

class BaseImage implements Image {
    private final String name;

    public BaseImage(String name) {
        this.name = name;
    }

    @Override
    public String render() {
        return "BaseImage(" + name + ")";
    }

    @Override
    public Image getWrapped() {
        return this;
    }
}

// Decorator
abstract class ImageDecorator implements Image {
    protected Image wrapped;

    public ImageDecorator(Image wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Image getWrapped() {
        return wrapped;
    }
}

class GrayscaleDecorator extends ImageDecorator {
    public GrayscaleDecorator(Image wrapped) { super(wrapped); }
    @Override
    public String render() { return "Grayscale[" + wrapped.render() + "]"; }
}

class SepiaDecorator extends ImageDecorator {
    public SepiaDecorator(Image wrapped) { super(wrapped); }
    @Override
    public String render() { return "Sepia[" + wrapped.render() + "]"; }
}

// Command
interface ImageCommand {
    void execute();
    void undo();
}

class ApplyFilterCommand implements ImageCommand {
    private ImageContainer container;
    private final String filter;

    public ApplyFilterCommand(ImageContainer container, String filter) {
        this.container = container;
        this.filter = filter;
    }

    @Override
    public void execute() {
        if ("SEPIA".equalsIgnoreCase(filter)) {
            container.image = new SepiaDecorator(container.image);
        } else if ("GRAYSCALE".equalsIgnoreCase(filter)) {
            container.image = new GrayscaleDecorator(container.image);
        }
    }

    @Override
    public void undo() {
        container.image = container.image.getWrapped(); // esquema exacto del taller
    }
}

// Contenedor mutable para el comando
class ImageContainer {
    public Image image;
    public ImageContainer(Image image) { this.image = image; }
}

public class Ejercicio10 {
    public static void main(String[] args) {
        ImageContainer canvas = new ImageContainer(new BaseImage("foto.png"));
        List<ImageCommand> history = new ArrayList<>();

        System.out.println("Original: " + canvas.image.render());

        // Aplicar Sepia
        ImageCommand cmd1 = new ApplyFilterCommand(canvas, "SEPIA");
        cmd1.execute();
        history.add(cmd1);
        System.out.println("Paso 1:   " + canvas.image.render());

        // Aplicar Grayscale
        ImageCommand cmd2 = new ApplyFilterCommand(canvas, "GRAYSCALE");
        cmd2.execute();
        history.add(cmd2);
        System.out.println("Paso 2:   " + canvas.image.render());

        // Deshacer última acción (Undo)
        ImageCommand lastCmd = history.remove(history.size() - 1);
        lastCmd.undo();
        System.out.println("Undo:     " + canvas.image.render());
    }
}