import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.Font
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class ITextPdfEditor {
    fun edit(boletoPath: String, logoPath: String, texto: List<String>, outputPath: String) {
        // Carregar o arquivo PDF original
        val reader = PdfReader(boletoPath)

        // Criar o arquivo PDF modificado
        val outputFile = File(outputPath)
        val document = Document(reader.getPageSizeWithRotation(1))
        val pdfWriter = PdfWriter.getInstance(document, FileOutputStream(outputFile))
        document.open()

        // Obter o ContentByte para a página
        val cb = pdfWriter.directContent

        // Copiar a página original para o novo documento
        val page = pdfWriter.getImportedPage(reader, 1)
        cb.addTemplate(page, 0f, 0f)

        // Desenhar um retângulo branco na parte superior da página
        val whiteRectHeight = 80f
        val whiteRect = Rectangle(0f, A4_HEIGHT - whiteRectHeight, A4_WIDTH, A4_HEIGHT)
        whiteRect.backgroundColor = BaseColor.WHITE
        cb.rectangle(whiteRect)

        // Carregar a imagem da logo e desenhá-la na página
        val imgWidth = 60f
        val imgMargin = 20f
        val logoImage = com.itextpdf.text.Image.getInstance(logoPath)
        logoImage.setAbsolutePosition(imgMargin, A4_HEIGHT - imgMargin - imgWidth)
        logoImage.scaleToFit(imgWidth, imgWidth)
        cb.addImage(logoImage)

        // Definir a fonte e cor do texto
        val fontSize = 10f
        val font = Font(Font.FontFamily.HELVETICA, fontSize, Font.BOLD)
        font.color = BaseColor.BLACK

        // Adicionar o texto à direita da logo
        val textTopMargin = 30f
        val textLeftMargin = 30f
        val lineSpacing = 4f

        // Posicionar o parágrafo na página
        val x = imgMargin + imgWidth + textLeftMargin
        val y = A4_HEIGHT - textTopMargin
        val contentStream = pdfWriter.directContent
        contentStream.saveState()
        contentStream.beginText()
        contentStream.setFontAndSize(BaseFont.createFont(), font.size)
        contentStream.setColorFill(font.color)
        contentStream.setTextMatrix(x, y)
        texto.forEach { line ->
            contentStream.showText(line)
            contentStream.moveText(0f, -fontSize -lineSpacing)
        }
        contentStream.endText()
        contentStream.restoreState()

        // Fechar o documento
        document.close()

        println("PDF modificado criado com sucesso!")
    }
}