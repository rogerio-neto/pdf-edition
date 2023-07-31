import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import java.awt.Color
import java.io.File

const val A4_HEIGHT = 842f
const val A4_WIDTH = 595f

class ApachePdfBoxPdfEditor {
    fun edit(boletoPath: String, logoPath: String, texto: List<String>, outputPath: String) {
         // Carregar o arquivo PDF original
        val inputFile = File(boletoPath)
        val originalDocument = PDDocument.load(inputFile)

        // Acessar a primeira página do PDF
        val firstPage = originalDocument.getPage(0)

        // Inicializar o ContentStream para editar a página existente
        val contentStream = PDPageContentStream(originalDocument, firstPage, PDPageContentStream.AppendMode.APPEND, true)

        // Desenhar um retângulo branco na parte superior da página
        val whiteRectHeight = 80f
        contentStream.setNonStrokingColor(Color.WHITE) // Define a cor do preenchimento para branco
        contentStream.addRect(0f, A4_HEIGHT - whiteRectHeight, A4_WIDTH, whiteRectHeight) // Define as coordenadas do retângulo a ser desenhado
        contentStream.fill()

        // Carregar a imagem da logo e desenhá-la na página
        val imgWidth = 60f
        val imgMargin = 20f
        val logoImage = PDImageXObject.createFromFile(logoPath, originalDocument)
        val logoX = imgMargin
        val logoY = A4_HEIGHT - imgMargin - imgWidth
        contentStream.drawImage(logoImage, logoX, logoY, imgWidth, imgWidth) // Define as coordenadas e o tamanho da imagem

        // Definir a cor do texto como preto
        contentStream.setNonStrokingColor(Color.BLACK)

        // Adicionar o texto à direita da logo
        val fontHeight = 10f
        val textTopMargin = 30f
        val textLeftMargin = 30f
        val lineSpacing = 4f
        contentStream.beginText()
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontHeight)
        contentStream.newLineAtOffset(logoX + imgWidth + textLeftMargin, A4_HEIGHT - textTopMargin) // Define as coordenadas para posicionar o texto
        for (line in texto) {
            contentStream.showText(line)
            contentStream.newLineAtOffset(0f, -fontHeight - lineSpacing)
        }
        contentStream.endText()

        // Fechar o ContentStream
        contentStream.close()

        // Salvar as modificações no mesmo arquivo
        originalDocument.save(outputPath)
        originalDocument.close()

        println("PDF modificado criado com sucesso!")
    }
}