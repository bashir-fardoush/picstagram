$(document).ready(function () {
    function followFriend(userId, friendId) {
        console.log(userId);
        console.log(friendId);

        var url = "${pageContext.request.contextPath}/api/v1/user/followfriend";

        //console.log(this);
        $.ajax({
            method: "POST",
            url: url,
            data: {userId: userId, friendId: friendId}
        }).done(function (response) {

            console.log(response);

            $("#suggestion_"+friendId).remove();


        }).fail(function () {

            console.log("failed to save friend");
        });


    }
    function addLike(postId, userId, element) {

        var url = "${pageContext.request.contextPath}/api/v1/post/like";

        //console.log(this);
        $.ajax({
            method: "POST",
            url: url,
            data: {postId: postId, userId: userId}
        }).done(function (response) {
            console.log(response);
            console.log("like saved");

            var totalLikeStr = document.getElementById("like_count_"+postId).innerHTML;
            var totalLike = parseInt(totalLikeStr);

            if (response > 0) {
                // liked state
                $(element).css("color", "#44d0b0");
                totalLike++;
            } else {
                // unliked state
                $(element).css("color", "#8d8d8d");
                totalLike--;
            }

            document.getElementById("like_count_"+postId).innerHTML = totalLike;


        }).fail(function () {

            console.log("failed to save like");
        });

    }
    function addComment(postId, userId) {

        var commentText = $('#comment_' + postId).val();
        var url = "${pageContext.request.contextPath}/api/v1/post/addcomment";


        $.ajax({
            method: "POST",
            url: url,
            data: {postId: postId, userId: userId, commentText: commentText}
        }).done(function (response) {

            console.log(response);
            if (response.id <= 0){
                console.log("Invalid comment id")
                return;
            }

            $('#comment_' + postId).val('');

            $('#comments_' + postId).append(" </hr> <div >\n" +
                "                <div class=\"media m-0\">\n" +
                "                 <div class=\"d-flex mr-3\">\n" +
                "                    <a href=\"\"><img class=\"img-fluid rounded-circle\"\n" +
                "                          src=\"${pageContext.request.contextPath }${user.avatar}\"\n" +
                "                     alt=\"User\"></a>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class=\"media-body\">\n" +
                "                  <p class=\"m-0\">" + commentText + "</p>\n" +
                "                     <small><span><i class=\"icon ion-md-pin\"></i>${user.fullName}</span></small>\n" +
                "            </div>\n" +
                "            </div>\n" +
                "            </div>");

            // end of ammend


            var totalCommentStr = document.getElementById("comment_count_"+postId).innerHTML;
            var totalComment = parseInt(totalCommentStr);
            totalComment++;
            document.getElementById("comment_count_"+postId).innerHTML = totalComment;


        }).fail(function () {

        });


    }
})