import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

  void cria(InputStream inputStream, String nomeArquivo) throws Exception {
    // leitura da imagem
    // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));

    // InputStream inputStream = new URL(
    // "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
    // .openStream();

    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // criar nova imagem em memória com transparência e com tamanho novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();

    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para nova imagem (em memória)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();

    graphics.drawImage(imagemOriginal, 0, 0, null);

    // configurar a fonte
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 96);
    graphics.setColor(Color.BLUE);
    graphics.setFont(fonte);

    // escrever uma frase na nova imagem
    graphics.drawString("10/10", 100, novaAltura - 100);

    // escrever a nova imagem em um arquivo
    var saida = "saida/" + nomeArquivo;
    ImageIO.write(novaImagem, "png", new File(saida));
  }

}
