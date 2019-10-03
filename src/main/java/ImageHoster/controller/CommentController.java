package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
    public String addComment (@PathVariable("imageId")Integer imageId, @RequestParam("comment") String comment, Model model, HttpSession session){
        System.out.println("adding comment "+comment);
        List<Comment> comments = new ArrayList<>();
        User user = (User) session.getAttribute("loggeduser");
        System.out.println("Logged user "+user);
        Comment commentDo = new Comment();
        Image image = imageService.getImage(imageId);
        System.out.println("Fetched image "+image.getTitle());
        commentDo.setText(comment);
        commentDo.setImage(image);
        commentDo.setUser(user);
        commentDo.setDate(new Date());
        comments.add(commentDo);

        image.setComments(comments);
        imageService.addComment(commentDo);
        System.out.println("Saved comment ");
        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments", image.getComments());
        return "images/image";
    }
}
