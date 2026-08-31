package dosw.semana4.PatronesCombinados;

import java.util.List;

// 1. Template Method: Define el esqueleto inmutable del algoritmo
abstract class ReportGenerator {

    // Método plantilla final: orquesta los 4 pasos obligatorios
    public final void generate(String reportTitle) {
        List<String> rawData = fetchData();
        List<String> processedData = processData(rawData);
        String formattedOutput = applyFormat(reportTitle, processedData);
        exportFile(formattedOutput);
    }

    // Paso 1 fijo: Obtención de datos crudos
    private List<String> fetchData() {
        System.out.println("[1/4 Fetch Data] Consultando registros transaccionales desde el Data Warehouse...");
        return List.of("Ingresos: $500,000", "Costos Operativos: $180,000", "Margen Neto: 64%");
    }

    // Paso 2 fijo: Procesamiento y limpieza
    private List<String> processData(List<String> raw) {
        System.out.println("[2/4 Process Data] Normalizando indicadores y calculando balances contables...");
        return raw.stream().map(String::toUpperCase).toList();
    }

    // Paso 3 abstracto: Variable según formato
    protected abstract String applyFormat(String title, List<String> data);

    // Paso 4 abstracto: Variable según destino de exportación
    protected abstract void exportFile(String formattedContent);
}

// Subclase Concreta: PDF
class PdfReport extends ReportGenerator {
    @Override
    protected String applyFormat(String title, List<String> data) {
        return "=== PDF DOCUMENT: " + title + " ===\n" +
               "[Header Vectorial: Enterprise Logo]\n" +
               String.join("\n- ", data) + "\n" +
               "[Footer: Hash Criptográfico de Auditoría]";
    }

    @Override
    protected void exportFile(String formattedContent) {
        System.out.println("[4/4 Export File] Renderizando stream binario en /exports/reporte.pdf:\n" + formattedContent);
    }
}

// Subclase Concreta: Excel
class ExcelReport extends ReportGenerator {
    @Override
    protected String applyFormat(String title, List<String> data) {
        return "=== EXCEL WORKBOOK (XLSX): " + title + " ===\n" +
               "Hoja1: Matriz de Celdas A1:C10 -> " + String.join(" | ", data);
    }

    @Override
    protected void exportFile(String formattedContent) {
        System.out.println("[4/4 Export File] Escribiendo libro OpenXML en /exports/reporte.xlsx:\n" + formattedContent);
    }
}

// Subclase Concreta: CSV
class CsvReport extends ReportGenerator {
    @Override
    protected String applyFormat(String title, List<String> data) {
        return "ID,INDICADOR,VALOR\n" +
               "1,INGRESOS,500000\n" +
               "2,COSTOS,180000\n" +
               "3,MARGEN,64%";
    }

    @Override
    protected void exportFile(String formattedContent) {
        System.out.println("[4/4 Export File] Volcando texto plano separado por comas en /exports/reporte.csv:\n" + formattedContent);
    }
}

// 2. Factory Method: Encapsula la decisión de instanciación dinámica
class ReportFactory {
    public static ReportGenerator create(String format) {
        return switch (format.toUpperCase()) {
            case "PDF" -> new PdfReport();
            case "EXCEL" -> new ExcelReport();
            case "CSV" -> new CsvReport();
            default -> throw new IllegalArgumentException("Formato de reporte no soportado: " + format);
        };
    }
}

public class Ejercicio03 {
    public static void main(String[] args) {
        System.out.println("=== GENERACIÓN DINÁMICA DE REPORTE PDF ===");
        ReportGenerator pdfReport = ReportFactory.create("PDF");
        pdfReport.generate("Balance Q3 2026");

        System.out.println("\n=== GENERACIÓN DINÁMICA DE REPORTE CSV ===");
        ReportGenerator csvReport = ReportFactory.create("CSV");
        csvReport.generate("Data Feed Analytics");
    }
}