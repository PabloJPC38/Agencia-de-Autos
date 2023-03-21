$(document).ready(function(){
    $(document).on('click', ".eBtn", function() {

        var id=$(this).val();
        $('updateModal').modal('show');
        $('#id').val(id);
        
    });

    $(document).on('click', ".delBtn", function() {

        var ide=$(this).val();
        $('deleteModal').modal('show');
        $('#ide').val(ide);
        
    });

});