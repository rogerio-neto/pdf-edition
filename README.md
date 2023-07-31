# pdf-edition
Exploring how to modify pdfs using two main libs: IText and Apache PDFBox. The two main libraries used to modify PDFs in java are **Apache PDFBox** and 
**IText, A Free Java PDF Library** respectively, acordding to [MVN Repository](https://mvnrepository.com/open-source/pdf-libraries). The results were the following.

## Original header
![Captura de Tela 2023-07-31 às 16 25 55](https://github.com/rogerio-neto/pdf-edition/assets/110910861/a59693b5-5724-4999-9045-47dffe8532a5)

## Header cover with IText
![Captura de Tela 2023-07-31 às 16 26 11](https://github.com/rogerio-neto/pdf-edition/assets/110910861/e94c198c-12eb-4f19-834c-bb86f87589ba)

## Header cover with Apache PDFBox
![Captura de Tela 2023-07-31 às 16 27 23](https://github.com/rogerio-neto/pdf-edition/assets/110910861/40656fd6-4a63-49b9-bab4-8a54d64572e0)


Both of them require a good number os calculations to correctly position the elements in the correct positions. The aproach used in both libs to cover the head was:

1. Place a white rectangle over the top of the page, covering all the information.
2. Place the image on the left side of the header.
3. Place the text on the right of the image.

# Possible problems found
At least in Apache PDFBox, it wasn't possible to use strings with line breaks and other especial characters. The lib demanded that each line should be placed
individually and the line breaks were done through code.

The old header content was covered by the rectangle, the logo and the new text and is not visible now but one can still select it and use seach tool to find the text. For example:
![Captura de Tela 2023-07-31 às 16 38 06](https://github.com/rogerio-neto/pdf-edition/assets/110910861/21b42cb3-a578-4414-870c-2dff098d119a)
