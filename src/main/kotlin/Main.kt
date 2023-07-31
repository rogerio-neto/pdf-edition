fun main(args: Array<String>) {
    val apachePdf = ApachePdfBoxPdfEditor()
    val itextPdf = ITextPdfEditor()
    val salutText = listOf(
        "Salut Wines Comercio Bebida LTDA",
        "Rua Jose da Silva Ribeiro, 420 - CEP: 06726-130 - São Paulo - SP",
        "CNPJ: 43.908.283/0001-87",
        "Pagamento processado via sistema Inventa Sales",
    )


    apachePdf.edit("src/main/resources/boleto.pdf", "src/main/resources/logo.png", salutText, "boleto-edited.pdf")
    itextPdf.edit("src/main/resources/boleto.pdf", "src/main/resources/logo.png", salutText, "boleto-edited2.pdf")
}